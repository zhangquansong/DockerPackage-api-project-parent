package com.clt.api.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @ClassName : CodeGenerator
 * @Author : zhangquansong
 * @Date : 2019/1/5 0005 下午 3:28
 * @Description :代码生成器
 **/
public class CodeGenerator {

    public static void main(String[] args) {
        String[] models = {"api-dao", "api-service", "api-web", "api-commons"};
        for (String model : models) {
            shell(model);
        }
    }

    private static void shell(String model) {
        File file = new File(model);
        String path = file.getAbsolutePath();

        //用来获取Mybatis-Plus.properties文件的配置信息
        final ResourceBundle rb = ResourceBundle.getBundle("mybatis-plus");

        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOpen(false);
        gc.setOutputDir(path + "/src/main/java");
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        gc.setAuthor("zhangquansong");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setUrl(rb.getString("url"));
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername(rb.getString("userName"));
        dsc.setPassword(rb.getString("password"));
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        if ("api-dao".equals(model)) {
            pc.setParent("com.clt.api");
            pc.setEntity("entity");
            pc.setMapper("mapper");
        } else if ("api-service".equals(model)) {
            pc.setParent("com.clt.api");
            pc.setServiceImpl("service.impl");
            pc.setService("service");
        } else if ("api-web".equals(model)) {
            pc.setParent("com.clt.api");
            pc.setController("controller");
        } else if ("api-commons".equals(model)) {
            pc.setParent("com.clt.api");
            pc.setController("param");
        }
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        List<FileOutConfig> focList = new ArrayList<>();

        if ("api-dao".equals(model)) {
            focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称
                    return rb.getString("OutputDirXml") + "/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
                }
            });
        }

        if ("api-commons".equals(model)) {
            focList.add(new FileOutConfig("templates/mybatis/paramCreate.java.vm.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称
                    return rb.getString("OutputDirParam") + "/" + tableInfo.getEntityName() + "CreateParam.java";
                }
            });
            focList.add(new FileOutConfig("templates/mybatis/paramEdit.java.vm.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称
                    return rb.getString("OutputDirParam") + "/" + tableInfo.getEntityName() + "EditParam.java";
                }
            });
        }
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 关闭默认 xml 生成，调整生成 至 根目录
        TemplateConfig tc = new TemplateConfig();
        if ("api-dao".equals(model)) {
            tc.setMapper("/templates/mybatis/mapper.java.vm.ftl");
            tc.setController(null);
            tc.setService(null);
            tc.setServiceImpl(null);
            tc.setXml(null);
        } else if ("api-service".equals(model)) {
            tc.setService("/templates/mybatis/service.java.vm.ftl");
            tc.setServiceImpl("/templates/mybatis/serviceImpl.java.vm.ftl");
            tc.setController(null);
            tc.setEntity(null);
            tc.setMapper(null);
            tc.setXml(null);
        } else if ("api-web".equals(model)) {
            tc.setController("/templates/mybatis/controller.java.vm.ftl");
            tc.setService(null);
            tc.setServiceImpl(null);
            tc.setEntity(null);
            tc.setMapper(null);
            tc.setXml(null);
        } else if ("api-commons".equals(model)) {
            tc.setController(null);
            tc.setService(null);
            tc.setServiceImpl(null);
            tc.setEntity(null);
            tc.setMapper(null);
            tc.setXml(null);
        }
        mpg.setTemplate(tc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setInclude(new String[]{rb.getString("tableName")});
        mpg.setStrategy(strategy);
        mpg.execute();

    }

}