package com.young.utils;

import java.util.ArrayList;
import java.util.List;

public class TreeUtil {


    public static List<MenuNode> toTree(List<MenuNode> menuNodeList,Integer first){
        List<MenuNode> menuNodes = new ArrayList<MenuNode>();
        //将得到的菜单集合进行遍历，first代表0级菜单id
        for (MenuNode child : menuNodeList) {
            //判断该菜单是否是一级菜单
            if (first.equals(child.getPid())){
                //如果是一级菜单就调用找子菜单的方法
                menuNodes.add(findChildren(child, menuNodeList));
            }
        }
        return menuNodes;
    }

    public static MenuNode findChildren(MenuNode parent,List<MenuNode> menuNodeList){
        //将传输进来的父级菜单进行递归直至子菜单没有下一级菜单为止，parent代表一级菜单
        for (MenuNode child : menuNodeList) {
            //判断当前父级菜单是否有子菜单
            if (parent.getId().equals(child.getPid())){
                //有子菜单
                if (parent.getChild() == null){
                    //子菜单集合还没创建，创建子菜单集合
                    parent.setChild(new ArrayList<MenuNode>());
                }
                //子菜单不为空，将子菜单调用寻找它的子菜单
                MenuNode children = findChildren(child, menuNodeList);
                parent.getChild().add(children);
            }
        }
        return parent;
    }
}
