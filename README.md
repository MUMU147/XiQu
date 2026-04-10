# 戏曲文创网站（Java Web）

这是一个基于 Java Servlet + Thymeleaf + MySQL 的课程项目，包含前台用户功能与后台管理功能，支持文创商品展示与票务信息管理。

## 项目结构

- `HomeServlet/`：核心 Java Web 工程（Maven）
- `HomeServlet/src/main/java/`：Servlet、DAO、实体类与工具类
- `HomeServlet/web/`：Web 资源与 `WEB-INF/web.xml`
- `tranditional.sql`：数据库结构与示例数据（位于仓库根目录）
- `SECURITY_OPEN_SOURCE_CHECKLIST.md`：开源安全清理说明

## 技术栈

- Java 17（`pom.xml` 当前配置）
- Servlet API 3.1
- Thymeleaf 3
- MySQL 8.x
- Maven
- Tomcat 9+（推荐）

## 主要功能

- 前台：
  - 用户注册、登录、找回相关流程
  - 商品浏览、收藏
  - 票务信息展示
- 后台：
  - 管理员登录
  - 用户管理（增删改查）
  - 商品管理（增删改查）
  - 票务管理（增删改查）

> 注：具体访问路径由各 Servlet 映射决定，可在 `HomeServlet/src/main/java/com/shumei/servlet/` 下查看。

## 环境准备

1. 安装 JDK 17、Maven、MySQL、Tomcat。
2. 创建数据库并导入初始化脚本：
   - 脚本文件：`tranditional.sql`
3. 修改数据库连接信息：
   - 文件：`HomeServlet/src/main/java/com/shumei/util/DBUtil.java`
   - 需要替换：
     - `YOUR_DATABASE_NAME`
     - `YOUR_USERNAME`
     - `YOUR_PASSWORD`
4. 确认 MySQL 驱动与数据库版本兼容（当前驱动类为 `com.mysql.jdbc.Driver`）。

## 第三方服务环境变量

项目中短信与邮件能力已改为通过环境变量读取敏感信息，请在运行环境中配置：

- `ALIBABA_CLOUD_ACCESS_KEY_ID`
- `ALIBABA_CLOUD_ACCESS_KEY_SECRET`
- `SMTP_USERNAME`
- `SMTP_APP_PASSWORD`

如不使用短信/邮件相关功能，可暂不配置，但触发对应功能时会失败。

## 本地运行（推荐方式）

1. 进入项目目录：

   ```bash
   cd 源代码/H/HomeServlet
   ```

2. 构建项目：

   ```bash
   mvn clean package
   ```

3. 将生成的 `war`（在 `target/`）部署到 Tomcat。
4. 启动 Tomcat，浏览器访问：

   ```text
   http://localhost:8080/HomeServlet/
   ```

> 若上下文路径不是 `HomeServlet`，请以 Tomcat 实际部署路径为准。

## 开发说明

- 视图前后缀在 `web.xml` 中配置：
  - prefix: `/view/`
  - suffix: `.html`
- 推荐使用 IntelliJ IDEA 导入 Maven 项目进行开发。
- 已在 `.gitignore` 中忽略 IDE 配置、`target/`、日志等文件。

## 安全提示

- 不要将真实密钥、数据库账号密码、邮箱授权码提交到仓库。
- 发布前建议按 `SECURITY_OPEN_SOURCE_CHECKLIST.md` 再检查一次。
- `tranditional.sql` 中包含示例账号数据，生产环境请替换并清理敏感信息。

## 已知事项

- `HomeServlet/src/main/java/Main.java` 为 IDE 默认示例入口，不参与 Web 业务逻辑。
- `pom.xml` 中存在部分历史依赖信息，可按实际部署环境进一步精简。

