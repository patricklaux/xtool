# xtool

## 1. 简介

xtool 是一个小小的 Java 工具集，遵循简单、可靠的原则，不求大而全，但求小而美。主要包含：

- 字符串、数值、集合、IO等工具类；
- 一些常用自定义注解；
- NLP 方向的数据结构。

## 2. 使用

### 2.1.Maven

```xml

<dependency>
    <groupId>com.igeeksky</groupId>
    <artifactId>xtool</artifactId>
    <version>1.0.3</version>
</dependency>
```

### 2.2.Gradle

```groovy
implementation 'com.igeeksky:xtool:1.0.3'
```

### 2.3.编译安装

#### 项目地址：

- https://github.com/patricklaux/xtool/

- https://gitee.com/igeeksky/xtool

首先 git clone 项目，然后执行 maven 命令安装

```shell
# git clone项目到本地
git clone https://github.com/patricklaux/xtool.git

# 执行maven命令编译
mvn clean install
```

## 3. 参与

### 3.1.分支介绍

| 分支     | 说明                         |
| -------- | ---------------------------- |
| **main** | 主分支，用于版本发布         |
| **dev**  | 开发分支，用于接受 PR 和修改 |

如您希望参与开发这个类库，请首先 fork 项目到您的仓库，修改 dev 分支并提交 pr，然后等待合并即可。

### 3.2.开发约定

1. 无第三方依赖；
2. 缩进采用空格；
3. 添加完整注释；
4. 编写单元测试并运行通过。

### 3.3.建议反馈

- [github](https://github.com/patricklaux/xtool/issues)
- [gitee](https://gitee.com/igeeksky/xtool/issues)

如您发现任何 bug，或希望添加某类工具，或有任何开发建议，欢迎提交issue。

**！！！总之，欢迎 pr，欢迎 issue！！！**

## 4. 更新日志

| 版本  | 说明                                                     |
| ----- | -------------------------------------------------------- |
| 1.0.3 | 1. 调整DigestUtils默认小写 |
| 1.0.2 | 1. 补充完整注释 |
| 1.0.1 | 1. 增加测试用例<br />2. 删除 Lists类 |
| 1.0.0 | 1. 添加常用工具类<br />2. 添加 ConcurrentHashTrie 字典树 |

