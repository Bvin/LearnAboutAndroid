# Android相关研究
----------
每个Module均代表某个问题的Best practice！

1.更改之前的提交者信息：<br>
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
```<br>
首先把.git目录下的git增加user节点填写正确的名字和油箱，然后在git bash执行以上脚本(记得替换名字油箱)，
执行完push，此时查log已经纠正了，但是远程仓库还没有，所以还要再push才可以。一定要先把项目的正确地址
改正，否则最后一条合并commit依然是你之前的配的user，以后的项目最后手动配置user，以免用的全局user，
如果不同项目实际user是不一样的话。

