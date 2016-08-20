package com.cnsoft.lxchild.algorithm;

import com.cnsoft.lxchild.onlinemap.MapBitmap;
import com.cnsoft.lxchild.onlinemap.OLFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

public class Algorithm {//算法类
    public static boolean pathFlag = false;//true 找到了路径
    public static int[] target = new int[]{0,0,0};
//    public int[] source = MapList.source;//出发点
//    public int[] target = MapList.target[0];//目标点
    public static int[] source = new int[]{11, 3, 1};
    public static int run=0;
    public static int orun=0;
    private static Double[] pos;
    public int[] newsource = new int[3];
    public  ArrayList<int[][]> searchProcess = new ArrayList<>();//搜索过程

    //记录到每个点的最短路径 for Dijkstra
    public  HashMap<String, ArrayList<int[][]>> hmPath = new HashMap<>();
    int mapId = 0;//地图编号
    int[][] map = MapList.map_f1;


    //A*用优先级队列
    PriorityQueue<int[][]> astarQueue=new PriorityQueue<int[][]>(100,new AStarComparator(this));
    Stack<int[][]> stack = new Stack<>();//深度优先所用栈
    public  HashMap<String, int[][]> hm = new HashMap<>();//结果路径记录
    LinkedList<int[][]> queue = new LinkedList<>();//广度优先所用队列
    //记录路径长度 for Dijkstra
    int[][] length = new int[MapList.map_f1.length][MapList.map_f1[0].length];
    int[][] visited = new int[MapList.map_f1.length][MapList.map_f1[0].length];//0 未去过 1 去过
    int[][] sequence = {
            {0, 1}, {0, -1},
            {-1, 0}, {1, 0},
            {-1, 1}, {-1, -1},
            {1, -1}, {1, 1}
    };
    int timeSpan = 10;//时间间隔

    public static void setPos(Double[] pos) {
        Algorithm.pos = pos;
    }

    public void clearState() {//清空所有状态与列表
        pathFlag = false;
        searchProcess.clear();
        stack.clear();
        queue.clear();
        astarQueue.clear();;
        hm.clear();
        visited = new int[MapList.map_f1.length][MapList.map_f1[0].length];
        hmPath.clear();
        for (int i = 0; i < length.length; i++) {
            for (int j = 0; j < length[0].length; j++) {
                length[i][j] = 9999;//初始路径长度为最大距离都不可能的那么大
            }
        }
    }

    public void runAlgorithm() {//运行算法
        clearState();


                //DijkstraAStar_f1();
                //BFSAStar();
        setWay(source[0],source[1],target[0],target[1]);
        run++;





        }
        //DijkstraAStar_f1();
        // DijkstraAStar();


//    public void BFSAStar(){//广度优先 A*算法
//        new Thread(){
//            public void run(){
//                boolean flag=true;
//                int[][] start={//开始状态
//                        {source[0],source[1]},
//                        {source[0],source[1]}
//                };
//                astarQueue.offer(start);
//                while(flag){
//                    int[][] currentEdge=astarQueue.poll();//从队首取出边
//                    int[] tempTarget=currentEdge[1];//取出此边的目的点
//                    //判断目的点是否去过，若去过则直接进入下次循环
//                    if(visited[tempTarget[1]][tempTarget[0]]!=0){
//                        continue;
//                    }
//                    //标识目的点为访问过
//                    visited[tempTarget[1]][tempTarget[0]]=visited[currentEdge[0][1]][currentEdge[0][0]]+1;
//                    searchProcess.add(currentEdge);//将临时目的点加入搜索过程中
//                    //记录此临时目的点的父节点
//                    hm.put(tempTarget[0]+":"+tempTarget[1],new int[][]{currentEdge[1],currentEdge[0]});
//                    MapBitmap.canDrawPath = true;
//                    try{Thread.sleep(timeSpan);}catch(Exception e){e.printStackTrace();}
//                    //判断有否找到目的点
//                    if(tempTarget[0]==target[0]&&tempTarget[1]==target[1]){
//                        break;
//                    }
//                    int currCol=tempTarget[0];//将所有可能的边入优先级队列
//                    int currRow=tempTarget[1];
//                    for(int[] rc:sequence){
//                        int i=rc[1];
//                        int j=rc[0];
//                        if(i==0&&j==0){continue;}
//                        if(currRow+i>=0&&currRow+i<MapList.map_f1.length&&currCol+j>=0
//                                &&currCol+j<MapList.map_f1[0].length&&
//                                map[currRow+i][currCol+j]!=1){
//                            int[][] tempEdge={
//                                    {tempTarget[0],tempTarget[1]},
//                                    {currCol+j,currRow+i}
//                            };
//                            astarQueue.offer(tempEdge);
//                        }
//                    }
//                }
//                pathFlag=true;
//                MapBitmap.canDrawPath = true;
//
//            }
//        }.start();
//    }

    public   HashMap<String,int[][]> setWay(final int sx,final int sy,final int tx, final int ty) {
        new Thread(){
            public void run(){
                boolean flag=true;
                int[][] start={//开始状态
                        {sx,sy},
                        {sx,sy}
                };
                astarQueue.offer(start);
                while(flag){
                    int[][] currentEdge=astarQueue.poll();//从队首取出边
                    int[] tempTarget=currentEdge[1];//取出此边的目的点
                    //判断目的点是否去过，若去过则直接进入下次循环
                    if(visited[tempTarget[1]][tempTarget[0]]!=0){
                        continue;
                    }
                    //标识目的点为访问过
                    visited[tempTarget[1]][tempTarget[0]]=visited[currentEdge[0][1]][currentEdge[0][0]]+1;
                    searchProcess.add(currentEdge);//将临时目的点加入搜索过程中
                    //记录此临时目的点的父节点
                    hm.put(tempTarget[0]+":"+tempTarget[1],new int[][]{currentEdge[1],currentEdge[0]});
                    MapBitmap.canDrawPath = true;
                    try{Thread.sleep(timeSpan);}catch(Exception e){e.printStackTrace();}
                    //判断有否找到目的点
                    if(tempTarget[0]==tx&&tempTarget[1]==ty){
                        break;
                    }
                    int currCol=tempTarget[0];//将所有可能的边入优先级队列
                    int currRow=tempTarget[1];
                    for(int[] rc:sequence){
                        int i=rc[1];
                        int j=rc[0];
                        if(i==0&&j==0){continue;}
                        if(currRow+i>=0&&currRow+i<MapList.map_f1.length&&currCol+j>=0
                                &&currCol+j<MapList.map_f1[0].length&&
                                map[currRow+i][currCol+j]!=1){
                            int[][] tempEdge={
                                    {tempTarget[0],tempTarget[1]},
                                    {currCol+j,currRow+i}
                            };
                            astarQueue.offer(tempEdge);
                        }
                    }
                }
                pathFlag=true;
                MapBitmap.canDrawPath = true;

            }
        }.start();

        return  hm;
    }

    }




