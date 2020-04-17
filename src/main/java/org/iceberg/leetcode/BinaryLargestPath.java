package org.iceberg.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiaoyuzhzh on 2020/4/10.
 */
public class BinaryLargestPath {

    private Map<TreeNode, Path> pathTable = new HashMap<>();

    private static int count = 0;

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(5);
        treeNode.left = new TreeNode(4);
        treeNode.left.left = new TreeNode(11);
        treeNode.left.left.left = new TreeNode(7);
        treeNode.left.left.right = new TreeNode(2);
        treeNode.right = new TreeNode(8);
        treeNode.right.left = new TreeNode(13);
        treeNode.right.right = new TreeNode(4);
        treeNode.right.right.right = new TreeNode(1);
        int i = new BinaryLargestPath().maxPathSum(treeNode);
        System.out.println(i);
        System.out.println(count);
    }

//    public static TreeNode getTreeByArray(Integer[] arr){
//        TreeNode root = new TreeNode(arr[0]);
//        for (int i = 1; i< arr.length; i ++) {
//            if(arr[i]!=null){
//
//            }
//        }
//    }

    /**
     * 设置当前树的左最大路径，并返回
     *
     * @param treeNode
     * @return
     */
    public int maxPathSum(TreeNode treeNode) {
        Integer sum = null;

        //先求解左右最大深度
        setLeftAndRightMax(treeNode);

        //设置向上最大值
        setUpMax(treeNode);

        //遍历求解最大的path
        for (Path value : pathTable.values()) {
            count ++;
            if(sum == null){
                sum = value.getMaxPath();
            }else {
                sum = Math.max(sum, value.getMaxPath());
            }
        }

        return sum;
    }

    private void setUpMax(TreeNode treeNode) {
        count ++;

        Path path = pathTable.get(treeNode);
        if(path.getUpMax() == null){
            path.setUpMax(treeNode.val);
        }
        if (treeNode.left != null) {
            Path leftPath = pathTable.get(treeNode.left);
            leftPath.setUpMax(Math.max(0, Math.max(path.getRightMax(),path.getUpMax())) + treeNode.left.val);
            setUpMax(treeNode.left);
        }
        if (treeNode.right != null) {
            Path rightPath = pathTable.get(treeNode.right);
            rightPath.setUpMax(Math.max(0, Math.max(path.getLeftMax(),path.getUpMax())) + treeNode.right.val);
            setUpMax(treeNode.right);
        }
    }

    private int setLeftAndRightMax(TreeNode treeNode) {
        int leftMax = 0, rightMax = 0;

        count ++;

        if (treeNode.left != null && pathTable.get(treeNode.left) == null) {
            leftMax = Math.max(setLeftAndRightMax(treeNode.left), 0);
        }
        if (treeNode.right != null && pathTable.get(treeNode.right) == null) {
            rightMax = Math.max(setLeftAndRightMax(treeNode.right), 0);
        }
        leftMax = leftMax + treeNode.val;
        rightMax = rightMax + treeNode.val;
        Path path = new Path();
        path.setLeftMax(leftMax);
        path.setRightMax(rightMax);
        pathTable.put(treeNode, path);
        return Math.max(leftMax, rightMax);
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Path {
    //左边最大路径
    private int leftMax;
    //右边最大路径
    private int rightMax;
    //上面最大路径
    private Integer upMax;

    public int getLeftMax() {
        return leftMax;
    }

    public void setLeftMax(int leftMax) {
        this.leftMax = leftMax;
    }

    public int getRightMax() {
        return rightMax;
    }

    public void setRightMax(int rightMax) {
        this.rightMax = rightMax;
    }

    public Integer getUpMax() {
        return upMax;
    }

    public void setUpMax(Integer upMax) {
        this.upMax = upMax;
    }

    public int getMaxPath(){
        return Math.max(upMax,Math.max(leftMax,rightMax));
    }
}


