#Spring Boot + freemarker + layui 简单练习项目



项目中有两个配置文件，默认是由application.properties；

实际开发中使用application-dev.properties；需要单独指定项目加载时使用的文件

可以在默认配置文件中配置spring.profiles.active = dev

也可在idea 中针对项目设置 :

工具栏打开 **Run/Debug Configurations** 在**Programarguments**中设置`--spring.profiles.active=dev`

来指定使用[application-dev.properties]
