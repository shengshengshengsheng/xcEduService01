<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Hello World!</title>
</head>
<body>
Hello ${name}!
<br/>
<table>
    <tr>
        <td>序号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>钱包</td>
    </tr>
    <#--FTL指令：和HTML标记类似，名字前加#予以区分，Freemarker会解析标签中的表达式或逻辑-->
    <#list stus as stu>
        <tr>
            <#--_index：得到循环的下标，使用方法是在stu后边加"_index"，它的值是从0开始-->
            <td>${stu_index + 1}</td>

            <#--if 指令即判断指令，是常用的FTL指令，freemarker在解析时遇到if会进行判断，
            条件为真则输出if中间的内容，否则跳过内容不再输出。-->
            <td <#if stu.name =='小明'>style="background:red;"</#if>>${stu.name}</td>
            <#--插值（Interpolation）：即${..}部分,freemarker会用真实的值代替${..}-->
            <td>${stu.age}</td>
            <td >${stu.mondy}</td>
        </tr>
    </#list>

</table>
<br/><br/>
输出stu1的学生信息：<br/>
姓名：${stuMap['stu1'].name}<br/>
年龄：${stuMap['stu1'].age}<br/>
输出stu1的学生信息：<br/>
姓名：${stu1.name}<br/>
年龄：${stu1.age}<br/>
遍历输出两个学生信息：<br/>
<table>
    <tr>
        <td>序号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>钱包</td>
    </tr>
<#list stuMap?keys as k>
<tr>
    <td>${k_index + 1}</td>
    <td>${stuMap[k].name}</td>
    <td>${stuMap[k].age}</td>
    <td >${stuMap[k].mondy}</td>
</tr>
</#list>
</table>
</br>
<table>
    <tr>
        <td>姓名</td>
        <td>年龄</td>
        <td>出生日期</td>
        <td>钱包</td>
        <td>最好的朋友</td>
        <td>朋友个数</td>
        <td>朋友列表</td>
    </tr>
    <#--判断某变量是否存在使用 “??” 用法为:variable??,如果该变量存在,返回true,否则返回false-->
    <#if stus??>
    <#list stus as stu>
        <tr>
            <td>${stu.name!''}</td>
            <td>${stu.age}</td>
            <td>${(stu.birthday?date)!''}</td>
            <td>${stu.mondy}</td>
            <td>${(stu.bestFriend.name)!''}</td>
            <td>${(stu.friends?size)!0}</td>
            <td>
                <#if stu.friends??>
                <#list stu.friends as firend>
                    ${firend.name!''}<br/>
                </#list>
                </#if>
            </td>
        </tr>
    </#list>
    </#if>

</table>
<br/>
<#assign text="{'bank':'工商银行','account':'10101920201920212'}" />
<#--内建函数语法格式： 变量+?+函数名称-->
<#assign data=text?eval />
开户行：${data.bank}  账号：${data.account}

</body>
</html>