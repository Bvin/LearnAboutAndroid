# Android相关研究
----------
每个Module均代表某个问题的Best practice！

1. 更改之前的提交者信息：<br>
```
#!/bin/sh

git filter-branch --env-filter '

OLD_EMAIL="your-old-email@example.com"
CORRECT_NAME="Your Correct Name"
CORRECT_EMAIL="your-correct-email@example.com"

if [ "$GIT_COMMITTER_EMAIL" = "$OLD_EMAIL" ]
then
    export GIT_COMMITTER_NAME="$CORRECT_NAME"
    export GIT_COMMITTER_EMAIL="$CORRECT_EMAIL"
fi
if [ "$GIT_AUTHOR_EMAIL" = "$OLD_EMAIL" ]
then
    export GIT_AUTHOR_NAME="$CORRECT_NAME"
    export GIT_AUTHOR_EMAIL="$CORRECT_EMAIL"
fi
' --tag-name-filter cat -- --branches --tags
```
<br>
首先把.git目录下的config增加user节点填写正确的名字和邮箱，然后在git bash执行以上脚本(记得替换名字邮箱)，
执行完push，此时查log已经纠正了，但是远程仓库还没有，所以还要再push才可以。一定要先把项目的正确地址
改正，否则最后一条合并commit依然是你之前的配的user，以后的项目最后手动配置user，以免用的全局user，
如果不同项目实际user是不一样的话。
>若出现以下错误：
$ git push -f github master
Counting objects: 2299, done.
Delta compression using up to 4 threads.
Compressing objects: 100% (1635/1635), done.
Writing objects: 100% (2299/2299), 793.46 KiB | 0 bytes/s, done.
Total 2299 (delta 975), reused 0 (delta 0)
remote: Resolving deltas: 100% (975/975), completed with 1 local object.
To https://github.com/Bvin/rainbow_analytics.git
 + a08ffb4...523651d master -> master (forced update)
直接 -f 强推


新建项目提交之前，记得一定要先把config的user改成github上的用户名

2. JitPack支持的gradle版本
project/build.gradle中
```
dependencies {
    classpath 'com.android.tools.build:gradle:2.2.2'
    classpath 'com.github.dcendents:android-maven-gradle-plugin:1.5'
    ...
}
```
在 /gradle/wrapper/gradle-wrapper.properties中指定
distributionUrl=https\://services.gradle.org/distributions/gradle-2.14.1-all.zip


