# The Transaction Concept：Virtues and Limitations论文阅读笔记

## 什么是事务？

论文中以一个非常简单的例子，讲述了这个概念。

事务概念源于合同法。在签订合同时，两个或多个当事方会进行一段时间的谈判，然后达成协议。该协议通过共同签署文件或其他行为（如简单的握手或点头）而具有约束力。如果当事方彼此相当怀疑或只是想要安全，他们会任命一个中介（通常称为托管官）来协调事务的承诺。

基督教婚礼仪式很好地展示了这样的契约。新郎新娘“协商”数天或数年，然后任命一位牧师来主持婚礼仪式。牧师首先询问是否有人对婚姻有任何异议；然后他询问新郎新娘是否同意婚姻。如果他们都说“我愿意”，他就宣布他们为夫妻。

当然，契约只是一个协议。个人可以违反契约，只要他们愿意违反法律。但在法律上，

契约（事务）只能在最初是非法的情况下被撤销。对不良事务的调整是通过进一步的补偿事务（包括法律救济）来完成的

事务概念具有以下属性：

- 一致性：交易双方必须遵循法律协议
- 原子性：它要么发生，要么不发生；要么所有人都受契约约束，要么没有人受约束。
- 持久性：一旦事务被提交，就无法撤销

## 事务的一般模型

系统提供的操作可以读取和变换记录和设备的值。一组构成一致状态变换的操作可以被归类为一个事务。事务保持系统一致性约束——它们通过将一致状态转变为新的一致状态来遵循这些法则。

事务必须是原子性和持久性；要么所有操作都完成，事务被称为提交；要么事务的所有影响都不再存在，事务被称为中止。

每个事务被定义为两个结果之一：提交或中止。已经提交的事务在故障时，影响仍然存在。另一个方面，任何已中止事务对其他事务的影响效果时不可见的。

![image-20240922144741933](/Users/cancaicai/Library/Application Support/typora-user-images/image-20240922144741933.png)

## 让故障变得罕见

这里主要是两个结论：

1. 不必构建一个完美的系统。一个每千年失败一次的系统就足够好了
2. 即使系统是完美的，某些事务也会因为数据输入错误、资金不足、操作员取消或超时而中止

论文中那些硬件分析在这里可以省略，说一下论文中得到的经验：

1. 模块的平均故障时间以月为单位进行测量
2. 模块可以设计为快速失败：要么正常工作，要么完全失效
3. 备用模块平均修复时间以秒或分钟为单位
4. 对这些模块进行双工处理使得平均故障时间可以达到世纪为单位

编写容错应用主要有两种策略

- 一个是让主进程在每次操作之前将其状态“检查点”保存到备份进程。如果主进程失败，备份进行将从主进程中断的地方继续对话。在这种情况下，重新请求者和服务器进程是非常微妙的。
- 另一种编写容错应用的策略是将计算的所有过程作为一个事务集合在一起，并在发生故障时将它们全部重置为初始事务状态。在发生故障的情况下，事务会被撤销（到一个保存点或到开始）并由一个新过程从该点继续。事务管理提供的回退和重启功能使应用程序员不必担心故障或过程对结果的影响