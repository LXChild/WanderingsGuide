package com.cnsoft.lxchild.algorithm;

/**
 * Created by Administrator on 2015/4/24.
 */
 public class Compare {
    public  int[] ntarget=new int[]{3,3};
    Algorithm algorithm;
    int[] Es1_0 = {6,40};
    int[] El1_1={7,42};
    int[] El2_2={37,42};
    int[] Es2_3={38,40};
    int[] El3_4={14,28};
    int[] Es3_5={15,29};
    int[] Es4_6={29,29};
    int[] El4_7={30,28};
    int[] El5_8={14,15};
    int[] Es5_9={15,15};
    int[] El6_10={29,15};
    int[] Es6_11={30,15};
    int[] El7_12={7,2};
    int[] Es7_13={6,3};
    int[] El8_14={37,2};
    int[] Es8_15={38,3};
    int[] EL3_b1 = {13, 2};
    int[] ES3_b1 = {14, 3};
    int[] ES4_b1 = {29, 3};
    int[] EL4_b1 = {30, 2};
    int[] ES1_b1 = {5, 14};
    int[] EL1_b1 = {6, 16};
    int[] EL2_b1 = {38, 16};
    int[] ES2_b1 = {39, 14};
    double[] dis=new double[20];

    public Compare() {
        algorithm =new Algorithm();
        if ((algorithm.source[2] == 1 && algorithm.target[2] != 0) || (algorithm.source[2] == 2 && algorithm.target[2] != 0)) {
            dis[0] = Math.sqrt((algorithm.target[0] - Es1_0[0]) * (algorithm.target[0] - Es1_0[0]) + (algorithm.target[1] - Es1_0[1]) * (algorithm.target[1] - Es1_0[1]));
            dis[1] = Math.sqrt((algorithm.target[0] - El1_1[0]) * (algorithm.target[0] - El1_1[0]) + (algorithm.target[1] - El1_1[1]) * (algorithm.target[1] - El1_1[1]));
            dis[2] = Math.sqrt((algorithm.target[0] - El2_2[0]) * (algorithm.target[0] - El2_2[0]) + (algorithm.target[1] - El2_2[1]) * (algorithm.target[1] - El2_2[1]));
            dis[3] = Math.sqrt((algorithm.target[0] - Es2_3[0]) * (algorithm.target[0] - Es2_3[0]) + (algorithm.target[1] - Es2_3[1]) * (algorithm.target[1] - Es2_3[1]));
            dis[4] = Math.sqrt((algorithm.target[0] - El3_4[0]) * (algorithm.target[0] - El3_4[0]) + (algorithm.target[1] - El3_4[1]) * (algorithm.target[1] - El3_4[1]));
            dis[5] = Math.sqrt((algorithm.target[0] - Es3_5[0]) * (algorithm.target[0] - Es3_5[0]) + (algorithm.target[1] - Es3_5[1]) * (algorithm.target[1] - Es3_5[1]));
            dis[6] = Math.sqrt((algorithm.target[0] - Es4_6[0]) * (algorithm.target[0] - Es4_6[0]) + (algorithm.target[1] - Es4_6[1]) * (algorithm.target[1] - Es4_6[1]));
            dis[7] = Math.sqrt((algorithm.target[0] - El4_7[0]) * (algorithm.target[0] - El4_7[0]) + (algorithm.target[1] - El4_7[1]) * (algorithm.target[1] - El4_7[1]));
            dis[8] = Math.sqrt((algorithm.target[0] - El5_8[0]) * (algorithm.target[0] - El5_8[0]) + (algorithm.target[1] - El5_8[1]) * (algorithm.target[1] - El5_8[1]));
            dis[9] = Math.sqrt((algorithm.target[0] - Es5_9[0]) * (algorithm.target[0] - Es5_9[0]) + (algorithm.target[1] - Es5_9[1]) * (algorithm.target[1] - Es5_9[1]));
            dis[10] = Math.sqrt((algorithm.target[0] - El6_10[0]) * (algorithm.target[0] - El6_10[0]) + (algorithm.target[1] - El6_10[1]) * (algorithm.target[1] - El6_10[1]));
            dis[11] = Math.sqrt((algorithm.target[0] - Es6_11[0]) * (algorithm.target[0] - Es6_11[0]) + (algorithm.target[1] - Es6_11[1]) * (algorithm.target[1] - Es6_11[1]));
            dis[12] = Math.sqrt((algorithm.target[0] - El7_12[0]) * (algorithm.target[0] - El7_12[0]) + (algorithm.target[1] - El7_12[1]) * (algorithm.target[1] - El7_12[1]));
            dis[13] = Math.sqrt((algorithm.target[0] - Es7_13[0]) * (algorithm.target[0] - Es7_13[0]) + (algorithm.target[1] - Es7_13[1]) * (algorithm.target[1] - Es7_13[1]));
            dis[14] = Math.sqrt((algorithm.target[0] - El8_14[0]) * (algorithm.target[0] - El8_14[0]) + (algorithm.target[1] - El8_14[1]) * (algorithm.target[1] - El8_14[1]));
            dis[15] = Math.sqrt((algorithm.target[0] - Es8_15[0]) * (algorithm.target[0] - Es8_15[0]) + (algorithm.target[1] - Es8_15[1]) * (algorithm.target[1] - Es8_15[1]));
            double min = dis[0];
            int m = 0;

            for (int i = 0; i < 16; i++) {
                if (dis[i] < min) {
                    min = dis[i];
                    m = i;

                }


            }
            switch (m) {
                case 0:
                    ntarget[0] = Es1_0[0];
                    ntarget[1] = Es1_0[1];
                    break;
                case 1:
                    ntarget[0] = El1_1[0];
                    ntarget[1] = El1_1[1];
                    break;
                case 2:
                    ntarget[0] = El2_2[0];
                    ntarget[1] = El2_2[1];
                    break;
                case 3:
                    ntarget[0] = Es2_3[0];
                    ntarget[1] = Es2_3[1];
                    break;
                case 4:
                    ntarget[0] = El3_4[0];
                    ntarget[1] = El3_4[1];
                    break;
                case 5:
                    ntarget[0] = Es3_5[0];
                    ntarget[1] = Es3_5[1];
                    break;
                case 6:
                    ntarget[0] = Es4_6[0];
                    ntarget[1] = Es4_6[1];
                    break;
                case 7:
                    ntarget[0] = El4_7[0];
                    ntarget[1] = El4_7[1];
                    break;
                case 8:
                    ntarget[0] = El5_8[0];
                    ntarget[1] = El5_8[1];
                    break;
                case 9:
                    ntarget[0] = Es5_9[0];
                    ntarget[1] = Es5_9[1];
                    break;
                case 10:
                    ntarget[0] = El6_10[0];
                    ntarget[1] = El6_10[1];
                    break;
                case 11:
                    ntarget[0] = Es6_11[0];
                    ntarget[1] = Es6_11[1];
                    break;
                case 12:
                    ntarget[0] = El7_12[0];
                    ntarget[1] = El7_12[1];
                    break;
                case 13:
                    ntarget[0] = Es7_13[0];
                    ntarget[1] = Es7_13[1];
                    break;
                case 14:
                    ntarget[0] = El8_14[0];
                    ntarget[1] = El8_14[1];
                    break;
                case 15:
                    ntarget[0] = Es8_15[0];
                    ntarget[1] = Es8_15[1];
                    break;
                default:
                    break;


            }

        } else if (algorithm.source[2] == 1 && algorithm.target[2] == 0) {
            dis[0] = Math.sqrt((algorithm.target[0] - EL1_b1[0]) * (algorithm.target[0] - EL1_b1[0]) + (algorithm.target[1] - EL1_b1[1]) * (algorithm.target[1] - EL1_b1[1]));
            dis[1] = Math.sqrt((algorithm.target[0] - ES1_b1[0]) * (algorithm.target[0] - ES1_b1[0]) + (algorithm.target[1] - ES1_b1[1]) * (algorithm.target[1] - ES1_b1[1]));
            dis[2] = Math.sqrt((algorithm.target[0] - EL2_b1[0]) * (algorithm.target[0] - EL2_b1[0]) + (algorithm.target[1] - EL2_b1[1]) * (algorithm.target[1] - EL2_b1[1]));
            dis[3] = Math.sqrt((algorithm.target[0] - ES2_b1[0]) * (algorithm.target[0] - ES2_b1[0]) + (algorithm.target[1] - ES2_b1[1]) * (algorithm.target[1] - ES2_b1[1]));
            dis[4] = Math.sqrt((algorithm.target[0] - EL3_b1[0]) * (algorithm.target[0] - EL3_b1[0]) + (algorithm.target[1] - EL3_b1[1]) * (algorithm.target[1] - EL3_b1[1]));
            dis[5] = Math.sqrt((algorithm.target[0] - ES3_b1[0]) * (algorithm.target[0] - ES3_b1[0]) + (algorithm.target[1] - ES3_b1[1]) * (algorithm.target[1] - ES3_b1[1]));
            dis[6] = Math.sqrt((algorithm.target[0] - EL4_b1[0]) * (algorithm.target[0] - EL4_b1[0]) + (algorithm.target[1] - EL4_b1[1]) * (algorithm.target[1] - EL4_b1[1]));
            dis[7] = Math.sqrt((algorithm.target[0] - ES4_b1[0]) * (algorithm.target[0] - ES4_b1[0]) + (algorithm.target[1] - ES4_b1[1]) * (algorithm.target[1] - ES4_b1[1]));
            double min = dis[0];
            int m = 0;
            for (int i = 0; i < 8; i++) {
                if (dis[i] < min) {
                    min = dis[i];
                    m = i;

                }
            }
            switch (m) {
                case 0:
                    ntarget[0] = El1_1[0];
                    ntarget[1] = El1_1[1];
                    break;
                case 1:
                    ntarget[0] = Es1_0[0];
                    ntarget[1] = Es1_0[1];
                    break;
                case 2:
                    ntarget[0] = El2_2[0];
                    ntarget[1] = El2_2[1];
                    break;
                case 3:
                    ntarget[0] = Es2_3[0];
                    ntarget[1] = Es2_3[1];
                    break;
                case 4:
                    ntarget[0] = El3_4[0];
                    ntarget[1] = El3_4[1];
                    break;
                case 5:
                    ntarget[0] = Es3_5[0];
                    ntarget[1] = Es3_5[1];
                    break;
                case 6:
                    ntarget[0] = El4_7[0];
                    ntarget[1] = El4_7[1];
                    break;
                case 7:
                    ntarget[0] = Es4_6[0];
                    ntarget[1] = Es4_6[1];
                    break;
                default:
                    break;
            }



        } else if (algorithm.source[2] == 0 && algorithm.target[2] == 1) {
            dis[0] = Math.sqrt((algorithm.target[0] - Es1_0[0]) * (algorithm.target[0] - Es1_0[0]) + (algorithm.target[1] - Es1_0[1]) * (algorithm.target[1] - Es1_0[1]));
            dis[1] = Math.sqrt((algorithm.target[0] - El1_1[0]) * (algorithm.target[0] - El1_1[0]) + (algorithm.target[1] - El1_1[1]) * (algorithm.target[1] - El1_1[1]));
            dis[2] = Math.sqrt((algorithm.target[0] - El2_2[0]) * (algorithm.target[0] - El2_2[0]) + (algorithm.target[1] - El2_2[1]) * (algorithm.target[1] - El2_2[1]));
            dis[3] = Math.sqrt((algorithm.target[0] - Es2_3[0]) * (algorithm.target[0] - Es2_3[0]) + (algorithm.target[1] - Es2_3[1]) * (algorithm.target[1] - Es2_3[1]));
            dis[4] = Math.sqrt((algorithm.target[0] - El3_4[0]) * (algorithm.target[0] - El3_4[0]) + (algorithm.target[1] - El3_4[1]) * (algorithm.target[1] - El3_4[1]));
            dis[5] = Math.sqrt((algorithm.target[0] - Es3_5[0]) * (algorithm.target[0] - Es3_5[0]) + (algorithm.target[1] - Es3_5[1]) * (algorithm.target[1] - Es3_5[1]));
            dis[6] = Math.sqrt((algorithm.target[0] - Es4_6[0]) * (algorithm.target[0] - Es4_6[0]) + (algorithm.target[1] - Es4_6[1]) * (algorithm.target[1] - Es4_6[1]));
            dis[7] = Math.sqrt((algorithm.target[0] - El4_7[0]) * (algorithm.target[0] - El4_7[0]) + (algorithm.target[1] - El4_7[1]) * (algorithm.target[1] - El4_7[1]));

            double min = dis[0];
            int m = 0;
            for (int i = 0; i < 8; i++) {
                if (dis[i] < min) {
                    min = dis[i];
                    m = i;

                }
            }
            switch (m) {
                case 0:
                    ntarget[0] = ES1_b1[0];
                    ntarget[1] = ES1_b1[1];
                    break;
                case 1:
                    ntarget[0] = EL1_b1[0];
                    ntarget[1] = EL1_b1[1];
                    break;
                case 2:
                    ntarget[0] = EL2_b1[0];
                    ntarget[1] = EL2_b1[1];
                    break;
                case 3:
                    ntarget[0] = ES2_b1[0];
                    ntarget[1] = ES2_b1[1];
                    break;
                case 4:
                    ntarget[0] = EL3_b1[0];
                    ntarget[1] = EL3_b1[1];
                    break;
                case 5:
                    ntarget[0] = ES3_b1[0];
                    ntarget[1] = ES3_b1[1];
                    break;
                case 6:
                    ntarget[0] = ES4_b1[0];
                    ntarget[1] = ES4_b1[1];
                    break;
                case 7:
                    ntarget[0] = EL4_b1[0];
                    ntarget[1] = EL4_b1[1];
                    break;
                default:
                    break;
            }


        } else if (algorithm.source[2] == 0 && algorithm.target[2] == 2) {
            dis[0] = Math.sqrt((algorithm.target[0] - Es1_0[0]) * (algorithm.target[0] - Es1_0[0]) + (algorithm.target[1] - Es1_0[1]) * (algorithm.target[1] - Es1_0[1]));
            dis[1] = Math.sqrt((algorithm.target[0] - El1_1[0]) * (algorithm.target[0] - El1_1[0]) + (algorithm.target[1] - El1_1[1]) * (algorithm.target[1] - El1_1[1]));
            dis[2] = Math.sqrt((algorithm.target[0] - El2_2[0]) * (algorithm.target[0] - El2_2[0]) + (algorithm.target[1] - El2_2[1]) * (algorithm.target[1] - El2_2[1]));
            dis[3] = Math.sqrt((algorithm.target[0] - Es2_3[0]) * (algorithm.target[0] - Es2_3[0]) + (algorithm.target[1] - Es2_3[1]) * (algorithm.target[1] - Es2_3[1]));
            dis[4] = Math.sqrt((algorithm.target[0] - El3_4[0]) * (algorithm.target[0] - El3_4[0]) + (algorithm.target[1] - El3_4[1]) * (algorithm.target[1] - El3_4[1]));
            dis[5] = Math.sqrt((algorithm.target[0] - Es3_5[0]) * (algorithm.target[0] - Es3_5[0]) + (algorithm.target[1] - Es3_5[1]) * (algorithm.target[1] - Es3_5[1]));
            dis[6] = Math.sqrt((algorithm.target[0] - Es4_6[0]) * (algorithm.target[0] - Es4_6[0]) + (algorithm.target[1] - Es4_6[1]) * (algorithm.target[1] - Es4_6[1]));
            dis[7] = Math.sqrt((algorithm.target[0] - El4_7[0]) * (algorithm.target[0] - El4_7[0]) + (algorithm.target[1] - El4_7[1]) * (algorithm.target[1] - El4_7[1]));

            double min = dis[0];
            int m = 0;
            for (int i = 0; i < 8; i++) {
                if (dis[i] < min) {
                    min = dis[i];
                    m = i;

                }
            }
            switch (m) {
                case 0:
                    ntarget[0] = ES1_b1[0];
                    ntarget[1] = ES1_b1[1];
                    break;
                case 1:
                    ntarget[0] = EL1_b1[0];
                    ntarget[1] = EL1_b1[1];
                    break;
                case 2:
                    ntarget[0] = EL2_b1[0];
                    ntarget[1] = EL2_b1[1];
                    break;
                case 3:
                    ntarget[0] = ES2_b1[0];
                    ntarget[1] = ES2_b1[1];
                    break;
                case 4:
                    ntarget[0] = EL3_b1[0];
                    ntarget[1] = EL3_b1[1];
                    break;
                case 5:
                    ntarget[0] = ES3_b1[0];
                    ntarget[1] = ES3_b1[1];
                    break;
                case 6:
                    ntarget[0] = ES4_b1[0];
                    ntarget[1] = ES4_b1[1];
                    break;
                case 7:
                    ntarget[0] = EL4_b1[0];
                    ntarget[1] = EL4_b1[1];
                    break;
                default:
                    break;
            }


        } else if (algorithm.source[2] == 2 && algorithm.target[2] == 0) {
            dis[0] = Math.sqrt((algorithm.target[0] - EL1_b1[0]) * (algorithm.target[0] - EL1_b1[0]) + (algorithm.target[1] - EL1_b1[1]) * (algorithm.target[1] - EL1_b1[1]));
            dis[1] = Math.sqrt((algorithm.target[0] - ES1_b1[0]) * (algorithm.target[0] - ES1_b1[0]) + (algorithm.target[1] - ES1_b1[1]) * (algorithm.target[1] - ES1_b1[1]));
            dis[2] = Math.sqrt((algorithm.target[0] - EL2_b1[0]) * (algorithm.target[0] - EL2_b1[0]) + (algorithm.target[1] - EL2_b1[1]) * (algorithm.target[1] - EL2_b1[1]));
            dis[3] = Math.sqrt((algorithm.target[0] - ES2_b1[0]) * (algorithm.target[0] - ES2_b1[0]) + (algorithm.target[1] - ES2_b1[1]) * (algorithm.target[1] - ES2_b1[1]));
            dis[4] = Math.sqrt((algorithm.target[0] - EL3_b1[0]) * (algorithm.target[0] - EL3_b1[0]) + (algorithm.target[1] - EL3_b1[1]) * (algorithm.target[1] - EL3_b1[1]));
            dis[5] = Math.sqrt((algorithm.target[0] - ES3_b1[0]) * (algorithm.target[0] - ES3_b1[0]) + (algorithm.target[1] - ES3_b1[1]) * (algorithm.target[1] - ES3_b1[1]));
            dis[6] = Math.sqrt((algorithm.target[0] - EL4_b1[0]) * (algorithm.target[0] - EL4_b1[0]) + (algorithm.target[1] - EL4_b1[1]) * (algorithm.target[1] - EL4_b1[1]));
            dis[7] = Math.sqrt((algorithm.target[0] - ES4_b1[0]) * (algorithm.target[0] - ES4_b1[0]) + (algorithm.target[1] - ES4_b1[1]) * (algorithm.target[1] - ES4_b1[1]));
            double min = dis[0];
            int m = 0;
            for (int i = 0; i < 8; i++) {
                if (dis[i] < min) {
                    min = dis[i];
                    m = i;

                }
            }
            switch (m) {
                case 0:
                    ntarget[0] = El1_1[0];
                    ntarget[1] = El1_1[1];
                    break;
                case 1:
                    ntarget[0] = Es1_0[0];
                    ntarget[1] = Es1_0[1];
                    break;
                case 2:
                    ntarget[0] = El2_2[0];
                    ntarget[1] = El2_2[1];
                    break;
                case 3:
                    ntarget[0] = Es2_3[0];
                    ntarget[1] = Es2_3[1];
                    break;
                case 4:
                    ntarget[0] = El3_4[0];
                    ntarget[1] = El3_4[1];
                    break;
                case 5:
                    ntarget[0] = Es3_5[0];
                    ntarget[1] = Es3_5[1];
                    break;
                case 6:
                    ntarget[0] = El4_7[0];
                    ntarget[1] = El4_7[1];
                    break;
                case 7:
                    ntarget[0] = Es4_6[0];
                    ntarget[1] = Es4_6[1];
                    break;
                default:
                    break;
            }


        }


    }








}
