package com.app.friendsinnaighorhood;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.friendsinnaighorhood.grapgh.GraphAdjList;
import com.app.friendsinnaighorhood.grapgh.Index;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apps on 31/5/16.
 */
public class RAdapter extends RecyclerView.Adapter<RAdapter.ViewHolder> {

    public static final int MAX_ROW_COL = MainActivity.GRID_MAX_ROW_COL;
    private int mCurrentSelectedItem =-1;

    private Context mContext;
    private List<GridDataItem>mGridData;

    private GraphAdjList mGridGraph;


    public RAdapter(@NonNull Context context,@NonNull List<GridDataItem>gridData){
        this.mGridData = gridData;
        this.mContext = context;
        generateGraph();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView =LayoutInflater.from(mContext).inflate(R.layout.grid_item_view,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBindDataWithViewHolder(mGridData.get(position));
    }

    @Override
    public int getItemCount() {

        return mGridData == null?0:mGridData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView numberView;
        GridDataItem dataItem;

        public ViewHolder(View itemView) {
            super(itemView);
            numberView = (TextView) itemView.findViewById(R.id.numberText);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onGridItemClick();
                }
            });
        }

        public void onBindDataWithViewHolder(GridDataItem item){
            this.dataItem = item;
            numberView.setText(""+ dataItem.getItem());
            if(dataItem.isShouldHighlight()){
                numberView.setTextSize(TypedValue.COMPLEX_UNIT_SP,24);
                numberView.setTypeface(null, Typeface.BOLD_ITALIC);
                numberView.setTextColor(ContextCompat.getColor(mContext,R.color.highligtedColor));

            }else {
                numberView.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
                numberView.setTypeface(null, Typeface.NORMAL);
                numberView.setTextColor(ContextCompat.getColor(mContext,R.color.normalColor));
            }

            if(mCurrentSelectedItem >=0 && mCurrentSelectedItem == getAdapterPosition()){
                numberView.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                numberView.setTypeface(null, Typeface.ITALIC);
                numberView.setTextColor(ContextCompat.getColor(mContext,R.color.colorPrimary));
            }
        }

        private void onGridItemClick(){
            refreshData();
            mCurrentSelectedItem = getAdapterPosition();
            highlightNeighbors(getAdapterPosition());
            RAdapter.this.notifyDataSetChanged();
        }
    }


    public void refreshData(){
        for (GridDataItem item : mGridData){
            item.setShouldHighlight(false);
        }
    }

    public void highlightNeighbors(int position){
        boolean[] visited = new boolean[mGridData.size()];
        visitRecursively(mGridGraph.getNeighbors(position), visited, position);
    }

    public void visitRecursively(List<Integer> neighbours, boolean[] visited, int v){
        visited[v] = true;
        Log.d("Visted node", " is "+ v);
        for(Integer w : neighbours){
            Log.d("neighour ", "neighors is "+ w);
            if(!visited[w] && mGridData.get(w).getItem() == mGridData.get(v).getItem()){
                mGridData.get(w).setShouldHighlight(true);
                Log.d("Highlighted item ", " is " + w);
                visitRecursively(mGridGraph.getNeighbors(w), visited, w);
            }
        }
    }


    public void generateGraph(){
        mGridGraph = new GraphAdjList();
        for (int i =0;i<mGridData.size();i++){
            mGridGraph.addVertex();
        }
        addEdges();
    }

    public void addEdges(){
      for(int i = 0 ;i< mGridData.size();i++){
          Index index = Util.getIndexRowCol(i,MAX_ROW_COL);
          Log.d("Edge","Adding Edge for"+ i);
          for(int r =index.getI()-1;r<=index.getI()+1;r++){
              for (int c = index.getJ()-1;c<= index.getJ()+1;c++){
                  if(r>=0 && r<MAX_ROW_COL&& c >=0 && c<MAX_ROW_COL){
                      if(index.getJ()==c && index.getI() == r){

                      }else{
                          Log.d("Edge","added"+r +","+c+"edge");
                          mGridGraph.addEdge(i, Util.getIndexFromRowCol(new Index(r, c), MAX_ROW_COL));
                      }
                  }
              }
          }
      }

        Log.d("graph",mGridGraph.toString());
    }
}
