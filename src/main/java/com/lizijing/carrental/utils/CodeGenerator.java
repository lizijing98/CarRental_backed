package com.lizijing.carrental.utils;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;

import java.util.Collections;
import java.util.Scanner;

/**
 * <p> 实体代码生成 </p>
 *
 * @author LiZijing
 * @date 2022/2/27
 */
public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
//        String dbName = scanner("库名");
        String dbName = "car_rental";
        String dbUrl = "jdbc:mysql://localhost:3306/" + dbName + "?autoReconnect=true&autoReconnectForPools=true&useUnicode=true&character_set_server=utf8mb4&serverTimezone=Asia/Shanghai";

        FastAutoGenerator
                // 数据源配置
                .create(new DataSourceConfig
                        .Builder(dbUrl, "root", "123456")
                        .dbQuery(new MySqlQuery())
                        .typeConvert(new MySqlTypeConvert())
                        .keyWordsHandler(new MySqlKeyWordsHandler()))
                // 全局配置
                .globalConfig(builder ->
                        builder.fileOverride()
                                .outputDir(projectPath + "/src/main/java")
                                .author("LiZijing")
                                .enableSwagger()
                                .dateType(DateType.TIME_PACK)
                                .commentDate("yyyy-MM-dd"))
                // 包配置
                .packageConfig(builder ->
                        builder.parent("com.lizijing.carrental")
                                .entity("bean")
                                .mapper("mapper")
                                .service("service")
                                .serviceImpl("service.impl")
                                .controller("controller")
                                .xml("mapper")
                                .pathInfo(Collections.singletonMap(OutputFile.mapperXml, projectPath + "/src/main/resources/mapper")))
                // 注入配置
                .injectionConfig(builder ->
                        builder.beforeOutputFile((tableInfo, objectMap) -> System.out.println("tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.size())))
                // 配置模板
                .templateEngine(new VelocityTemplateEngine())
                // 策略配置
                .strategyConfig(builder ->
                        builder.enableCapitalMode()
                                .addInclude(scanner("表名，多个英文逗号分割").split(","))
                                .addTablePrefix("tb_", "sys_")
                                // Bean 策略配置
                                .entityBuilder()
                                .enableChainModel()
                                .enableLombok()
                                .enableTableFieldAnnotation()
                                .naming(NamingStrategy.underline_to_camel)
                                .columnNaming(NamingStrategy.underline_to_camel)
                                .logicDeleteColumnName("is_deleted")
                                .idType(IdType.AUTO)
                                .addTableFills(new Column("create_time", FieldFill.INSERT))
                                .addTableFills(new Column("update_time", FieldFill.INSERT_UPDATE))
                                // Controller 策略配置
                                .controllerBuilder()
                                .enableHyphenStyle()
                                .enableRestStyle()
                                // Mapper 策略配置
                                .mapperBuilder()
                                .enableBaseResultMap()
                                .superClass(BaseMapper.class)
                                .enableMapperAnnotation()
                                .formatXmlFileName("%sMapper")
                                // Service 策略配置
                                .serviceBuilder()
                                .formatServiceFileName("%sService")
                                .formatServiceImplFileName("%sServiceImpl"))
                .execute();
    }

}
