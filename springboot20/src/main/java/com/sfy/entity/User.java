package com.sfy.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 数据实体类层
 */
@Slf4j
@Data // @Data = @Getter + @Setter
public class User {
    private Integer id;
    private String name;
    private Integer age;
    // 需要生成get和set方法
    // lombok底层使用字节码技术ASM 帮你修改字节码文件，生成get和set方法


    @Override
    public String toString() {
        return this.getName() + ";" + this.getAge();
    }

    // 需要生成get和set方法 最终编译的时候还是会生成get和set方法
    // lombok 一定要在开发工具安装 在编译的时候修改字节码文件（底层使用字节码技术），线上环境使用编译好的文件
    // lombok性能不好
    public static void main(String[] args){
        User user = new User();
        user.setName("shao");
        user.setAge(20);
        System.out.println(user.toString());
    }
}
