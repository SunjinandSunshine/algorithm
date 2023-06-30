package code;

import java.util.List;

/**
 * @Author SunnyJ
 * @Date 2023/2/27 13:05
 */
public class StructNode {
    // 节点名称
    private String name;
    // 节点别名，根据同构类型报文标签名不同，需要将标签名映射成统一版标签名情况
    private String aliasName;
    // 节点属性类型
    private String attrType;
    // 多叉树的孩子节点信息
    private List<StructNode> children;
}
