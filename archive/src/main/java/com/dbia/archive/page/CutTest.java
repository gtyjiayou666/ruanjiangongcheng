package com.dbia.archive.page;
import com.dbia.archive.model.dir_inf;
import com.dbia.archive.model.pro_ma;
import com.dbia.archive.service.dir_infService;
import com.dbia.archive.service.file_infService;
import com.dbia.archive.service.pro_maService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CutTest {
    @Autowired
    private dir_infService dir_infService;
    @Autowired
    private pro_maService pro_maService;
    @Autowired
    private com.dbia.archive.service.file_infService file_infService;
    // 删除某个目录及目录下的所有子目录和文件

    public boolean deleteDir(File dir) {
        // 如果是文件夹
        if (dir.isDirectory()) {
            // 则读出该文件夹下的的所有文件
            String[] children = dir.list();
            // 递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                // File f=new File（String parent ，String child）
                // parent抽象路径名用于表示目录，child 路径名字符串用于表示目录或文件。
                // 连起来刚好是文件路径
                boolean isDelete = deleteDir(new File(dir, children[i]));
                // 如果删完了，没东西删，isDelete==false的时候，则跳出此时递归
                if (!isDelete) {
                    return false;
                }
            }
        }
        // 读到的是一个文件或者是一个空目录，则可以直接删除
        return dir.delete();
    }

    // 复制某个目录及目录下的所有子目录和文件到新文件夹
    public void copyFolder(String oldPath, String newPath) {
        try {
            // 如果文件夹不存在，则建立新文件夹
            (new File(newPath)).mkdirs();
            // 读取整个文件夹的内容到file字符串数组，下面设置一个游标i，不停地向下移开始读这个数组
            File filelist = new File(oldPath);
            String[] file = filelist.list();
            // 要注意，这个temp仅仅是一个临时文件指针
            // 整个程序并没有创建临时文件
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                // 如果oldPath以路径分隔符/或者\结尾，那么则oldPath/文件名就可以了
                // 否则要自己oldPath后面补个路径分隔符再加文件名
                // 谁知道你传递过来的参数是f:/a还是f:/a/啊？
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file[i]);
                } else {
                    temp = new File(oldPath + File.separator + file[i]);
                }

                // 如果游标遇到文件
                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    // 复制并且改名
                    FileOutputStream output = new FileOutputStream(newPath
                            + "/" + (temp.getName()).toString());
                    byte[] bufferarray = new byte[1024 * 64];
                    int prereadlength;
                    while ((prereadlength = input.read(bufferarray)) != -1) {
                        output.write(bufferarray, 0, prereadlength);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                // 如果游标遇到文件夹
                if (temp.isDirectory()) {
                    copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
                }
            }
        } catch (Exception e) {
            System.out.println("复制整个文件夹内容操作出错");
        }
    }

    public void moveFolder(String oldPath, String newPath) {
        // 先复制文件
        copyFolder(oldPath, newPath);
        // 则删除源文件，以免复制的时候错乱
        deleteDir(new File(oldPath));
    }

    public ChildDTOX searchFolder(int dir_id){
        if(dir_id==0)
            return null;
        ChildDTOX childDTOX=new ChildDTOX();
        childDTOX.setTitle(dir_infService.selectname(dir_id));
        childDTOX.setIcon("fa fa-folder");
        childDTOX.setHref("table.html?dir_id="+dir_id);
        childDTOX.setId(String.valueOf(dir_id));
        List<ChildDTOX> childDTOXList=new ArrayList<>();
        int[] x=dir_infService.selectparentid(dir_id);
        for (int i:x) {
            System.out.println(i);
            ChildDTOX ch=searchFolder(i);
            if(ch!=null)
                childDTOXList.add(ch);
        }
        childDTOX.setChild(childDTOXList);
        return childDTOX;
    }
    public ChildDTOX searchFolder1(int dir_id, int userid){
        if(dir_id==0 || pro_maService.selecttrue(userid,dir_id,1) == null)
            return null;
        ChildDTOX childDTOX=new ChildDTOX();
        childDTOX.setTitle(dir_infService.selectname(dir_id));
        childDTOX.setIcon("fa fa-folder");
        childDTOX.setHref("table.html?dir_id="+dir_id);
        childDTOX.setId(String.valueOf(dir_id));
        List<ChildDTOX> childDTOXList=new ArrayList<>();
        int[] x=dir_infService.selectparentid(dir_id);
        for (int i:x) {
            ChildDTOX ch=searchFolder1(i, userid);
            if(ch!=null)
                childDTOXList.add(ch);
        }
        childDTOX.setChild(childDTOXList);
        return childDTOX;
    }
    public DTree DTreesearchFolder(int dir_id){
        if(dir_id==0)
            return null;
        CheckArr checkArr = new CheckArr();
        checkArr.setChecked(String.valueOf(0));
        checkArr.setType(String.valueOf(1));
        List<CheckArr> CheckArr =new ArrayList<>();
        CheckArr.add(checkArr);
        CheckArr checkArr1 = new CheckArr();
        checkArr1.setChecked(String.valueOf(0));
        checkArr1.setType(String.valueOf(3));
        CheckArr.add(checkArr1);
        CheckArr checkArr2 = new CheckArr();
        checkArr2.setChecked(String.valueOf(0));
        checkArr2.setType(String.valueOf(5));
        CheckArr.add(checkArr2);
        CheckArr checkArr3 = new CheckArr();
        checkArr3.setChecked(String.valueOf(0));
        checkArr3.setType(String.valueOf(7));
        CheckArr.add(checkArr3);
        DTree dTree=new DTree();
        dir_inf dir_inf=dir_infService.idselect(dir_id);
        dTree.setId(String.valueOf(dir_inf.getId()));
        dTree.setTitle(dir_inf.getDir_name());
        dTree.setSpread(false);
        dTree.setParentId(String.valueOf(dir_inf.getParent_dir()));
        dTree.setCheckArr(CheckArr);
        List<DTree> DTreeList=new ArrayList<>();
        int[] x=dir_infService.selectparentid(dir_id);
        int num=0;
        for (int i:x) {
            DTree ch=DTreesearchFolder(i);
            if(ch!=null)
            {
                DTreeList.add(ch);
                num++;
            }
        }
        if(num==0)
            dTree.setLast(true);
        else
            dTree.setLast(false);
        dTree.setChildren(DTreeList);
        return dTree;
    }
    public DTree DTreesearchFolder1(int dir_id, int userid){
        if(dir_id==0)
            return null;
        CheckArr checkArr = new CheckArr();
        if(pro_maService.selecttrue(userid,dir_id,1) != null)
            checkArr.setChecked(String.valueOf(1));
        else
            checkArr.setChecked(String.valueOf(0));
        checkArr.setType(String.valueOf(1));
        List<CheckArr> CheckArr =new ArrayList<>();
        CheckArr.add(checkArr);
        CheckArr checkArr1 = new CheckArr();
        if(pro_maService.selecttrue(userid,dir_id,3) != null)
            checkArr1.setChecked(String.valueOf(1));
        else
            checkArr1.setChecked(String.valueOf(0));
        checkArr1.setType(String.valueOf(3));
        CheckArr.add(checkArr1);
        CheckArr checkArr2 = new CheckArr();
        if(pro_maService.selecttrue(userid,dir_id,5) != null)
            checkArr2.setChecked(String.valueOf(1));
        else
            checkArr2.setChecked(String.valueOf(0));
        checkArr2.setType(String.valueOf(5));
        CheckArr.add(checkArr2);
        CheckArr checkArr3 = new CheckArr();
        if(pro_maService.selecttrue(userid,dir_id,7) != null)
            checkArr3.setChecked(String.valueOf(1));
        else
            checkArr3.setChecked(String.valueOf(0));
        checkArr3.setType(String.valueOf(7));
        CheckArr.add(checkArr3);
        DTree dTree=new DTree();
        dir_inf dir_inf=dir_infService.idselect(dir_id);
        dTree.setId(String.valueOf(dir_inf.getId()));
        dTree.setTitle(dir_inf.getDir_name());
        dTree.setSpread(false);
        dTree.setParentId(String.valueOf(dir_inf.getParent_dir()));
        dTree.setCheckArr(CheckArr);
        List<DTree> DTreeList=new ArrayList<>();
        int[] x=dir_infService.selectparentid(dir_id);
        int num=0;
        for (int i:x) {
            DTree ch=DTreesearchFolder1(i,userid);
            if(ch!=null)
            {
                DTreeList.add(ch);
                num++;
            }
        }
        if(num==0)
            dTree.setLast(true);
        else
            dTree.setLast(false);
        dTree.setChildren(DTreeList);
        return dTree;
    }
    public void changeallpath(int dir_id, String path, String truepath){
        if(dir_id==0)
            return;
        String name=dir_infService.selectname(dir_id);
        dir_infService.changepath(dir_id,path+"/"+name,truepath+"/"+name);
        int[] x=dir_infService.selectparentid(dir_id);
        for (int i:x) {
            changeallpath(i,path+"/"+name,truepath+"/"+name);
        }
        return;
    }
    public void deletedir(int dir_id){
        if(dir_id==0)
            return;
        int[] x=dir_infService.selectparentid(dir_id);
        for (int i:x) {
            deletedir(i);
        }
        file_infService.diriddelete(dir_id);
        dir_infService.diriddelete(dir_id);
        return;
    }
}

