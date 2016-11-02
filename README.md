# kapokframework

### 部署kapok-base应用

1. 在你的MySQL里创建数据库kapok-base
    ```sql
    create database kapok-base;
    ```
    
2. 把kapok-base/script里的kapok_base.sql导入到新创建的数据库中
    ```sql
    source kapok_base.sql
    ```

3. 修改kapok-base应用里application.properties中的数据库地址与用户密码
    ```sql
    c3p0.jdbcUrl=jdbc:mysql://127.0.0.1:3306/kapok_base?useUnicode=true&amp;characterEncoding=UTF-8
    c3p0.user=root
    c3p0.password=root
    ```
