/*
 * @Author      : daguan814
 * @date        : 2022/12/5 17:23
 * @Description :
 */


package org.shuijing.guiguijizhang;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;

public class ApiDocs {
    public static void main(String[] args) {
        DocsConfig config = new DocsConfig();
        config.setProjectPath("/Users/shuijing/Documents/Code/Spring/GuiGuiAccounting"); // 项目根目录
        config.setProjectName("guiguijizhang"); // 项目名称
        config.setApiVersion("API文档");       // 声明该API的版本
        config.setDocsPath("/Users/shuijing/Documents/Code/Spring/GuiGuiAccounting/src/main/java/org/shuijing/guiguijizhang"); // 生成API 文档所在目录
        config.setAutoGenerate(Boolean.TRUE);  // 配置自动生成
        Docs.buildHtmlDocs(config); // 执行生成文档
    }
}
