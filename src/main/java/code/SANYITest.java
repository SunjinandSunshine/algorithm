package code;

/**
 * @Author SunnyJ
 * @Date 2022/9/13 10:33
 */
public class SANYITest {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val  = val;
        }
    }
    public static TreeNode lowestCommonAncestor(TreeNode root,TreeNode p, TreeNode q) {
        if(root == null || p == root || q == root) return root;
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if(left != null && right != null) return root;
        else if(left == null && right == null) return null;
        else return left == null ? right :left;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        TreeNode resNode = lowestCommonAncestor(root,root.left,root.right);
        System.out.println(resNode.val);
    }
}
