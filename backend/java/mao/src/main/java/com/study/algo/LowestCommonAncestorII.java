package com.study.algo;
//https://leetcode.ca/all/1644.html
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//后根序遍历完所有而不是一开始返回，确定是否存在的套路
public class LowestCommonAncestorII {
    // 计数找到了p和q的多少个节点
    private int count = 0;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode res = lca(root, p, q);
        // 如果都找到了，就可以返回res了，否则说明某个节点不存在，返回null
        return count == 2 ? res : null;
    }

    // 功能是返回p与q都存在的情况下的lca
    private TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        // 在左右两边找p和q
        TreeNode left = lca(root.left, p, q), right = lca(root.right, p, q);

        // 如果当前树根就是p或q，那计数加1，并且root就是lca（在p和q都存在于树的情况下）
        if (root == p || root == q) {
            count++;
            return root;
        }

        // 如果左子树里找不到p和q，那lca就在右边，如果右子树找不到p和q那lca就在左边，
        // 否则就是左右都找到了，返回当前树根
        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            return root;
        }
    }
}
