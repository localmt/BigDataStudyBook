# BigDataStudyBook
## 二次排序实验中的问题
 * 首先，需要注意的是：`
Error：The method setInputPaths(JobConf, String) in the type FileInputFormat is not`
遇到此错误时，要注意`FileInputFormat`中的包为`org.apache.hadoop.mapreduce.lib.input.FileInputFormat`中的包，以`mapred`为中间字符的包是旧包，不支持使用`job`作为参数。
 * 第二点要注意的是：
 有的人使用`\t`作为切割字符串的格式时，会报错，此时把`\t`改为`    `四个空格可以使用，注意数据集中的数据也要改为四个空格。