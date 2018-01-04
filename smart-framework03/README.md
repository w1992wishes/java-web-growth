# smart-framework 框架优化

首先将smart-framework02全部拷贝到smart-framework03中。

## 一、提供文件上传特性

1. 定义一个FileParam，封装文件参数；
2. 同时定义FormParam，封装表单参数，用于区分文件参数；
3. 一个表单中，所有的参数可分为两类：表单参数和文件参数，所以需要重构Param；
4. 接着开始上传代码的编写，先引入相应的依赖commons-fileupload；
5. 编写UploadHelper封装上传相关代码；
6. 在DispatcherServlet的init方法中调用UploadHelper.init方法；
7. 然后重构DispatcherServlet，区分Param的创建，如果不是isMultipart(request),则利用新建RequestHelper的创建param，
否则利用UploadHelper创建Param;