## xtool

[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html) [![Release](https://img.shields.io/github/v/release/patricklaux/xtool)](https://github.com/patricklaux/xtool/releases) [![Maven](https://img.shields.io/maven-central/v/com.igeeksky.xtool/xtool.svg)](https://central.sonatype.com/artifact/com.igeeksky.xtool/xtool) [![codecov](https://codecov.io/gh/patricklaux/xtool/branch/main/graph/badge.svg?token=VJ87A1IAVH)](https://codecov.io/gh/patricklaux/xtool) [![Last commit](https://img.shields.io/github/last-commit/patricklaux/xtool)](https://github.com/patricklaux/xtool/commits) [![Join the chat at https://gitter.im/igeeksky/xtool](https://badges.gitter.im/igeeksky/xtool.svg)](https://gitter.im/igeeksky/xtool?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

## 1. 简介

xtool 是一个小小的 Java 工具集，遵循简单、可靠的原则，不求大而全，但求小而美。主要包含：

- 字符串、数值、集合、IO等工具类；
- 一些常用自定义注解；
- NLP 相关的数据结构（主要实现：支持并发的高性能的字典树）

## 2. 使用

详细的使用介绍，请查看 [参考文档](https://github.com/patricklaux/xtool/blob/main/docs/Reference.md) 。

### 2.1.Maven

```xml
<dependency>
    <groupId>com.igeeksky.xtool</groupId>
    <artifactId>xtool</artifactId>
    <version>1.2.1</version>
</dependency>
```

### 2.2.Gradle

```groovy
implementation group: 'com.igeeksky.xtool', name: 'xtool', version: '1.2.1'
```

### 2.3.编译安装

#### 项目地址

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
4. 加上您的大名；
5. 编写单元测试并运行通过。

### 3.3.建议反馈

- [github](https://github.com/patricklaux/xtool/issues)
- [gitee](https://gitee.com/igeeksky/xtool/issues)

如您发现任何 bug，或希望添加某类工具，或有任何开发建议，欢迎提交issue。

**！！！总之，欢迎 pr，欢迎 issue！！！**



## 4. 更新日志

### 1.2.1

* `Futures` InterruptedException: interrupt currentThread

### 1.2.0

* change `Futures` API params

### 1.1.3

* add `AsyncCloseable` 

### 1.1.2

* add `GracefulShutdown` 

### 1.1.1

* add `RingBuffer`
* add `Futures` testcase

### 1.1.0

* remove `@ParameterNames`
* add `ObjectUtils`
* tuple move to root package

### 1.0.22

* add `ImmutableByteArray`

### 1.0.21

* `KeyValue` 和 `ByteArray` 增加静态方法

### 1.0.20

* `Futures` 增加 awaitAll 方法

### 1.0.19

* 优化 `SimpleJSON` 实现：如果 field 为 public，即使无 readMethod，也读取属性值

### 1.0.18

* 优化 `RandomUtils` 实现，通过 threadId 计算 index ，并获取数组对应 index 的 Random 实例

### 1.0.17

* 优化 `SimpleJSON` 实现，仅获取 readMethod，支持非标准 JavaBean

### 1.0.16

* remove `ThreadLocalRandom`，change to Random[]

### 1.0.14

* add `Codec`

* add `Compressor`

### 1.0.13

* add `Futures`

* add `ByteArray`

* add `KeyValue` & `ExpireKeyValue`

### 1.0.11

* 添加 `ConcurrentHashSet`
* 添加 `PlatformThreadFactory`
* 添加 `VirtualThreadFactory`
* 升级为支持 JDK 21，不再支持 JDK 17

### 1.0.10

* 添加 `SimpleJSON`（仅实现对象转 JSONString）
* 升级为支持 JDK 17，不再支持 JDK 8

### 1.0.9

* 添加 `RandomUtils`

### 1.0.8

* 添加 Maps.newHashMap方法

### 1.0.7

* 添加 IOUtils.closeQuietly方法

### 1.0.6

* `Found` 删除 node 字段，`NodeHelper` 增加精确匹配方法

### 1.0.5

* 调整部分方法返回值

### 1.0.4

* 增加测试用例

* 编写参考文档

* 调整部分代码

### 1.0.3

* 调整 `DigestUtils` 默认小写

### 1.0.2

* 补充注释

### 1.0.1

* 增加测试用例
* 删除 `Lists` 

### 1.0.0

* 常用工具
* 字典树

