/*
 Navicat Premium Data Transfer

 Source Server         : 段国磊1
 Source Server Type    : MySQL
 Source Server Version : 50738
 Source Host           : localhost:3306
 Source Schema         : library_manage_springboot

 Target Server Type    : MySQL
 Target Server Version : 50738
 File Encoding         : 65001

 Date: 01/06/2024 14:46:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `is_delete` bit(1) NOT NULL,
  `isbn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` double NOT NULL,
  `publisher` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `category_id` bigint(20) NULL DEFAULT NULL,
  `publish_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` bit(1) NULL DEFAULT NULL,
  `borrow_count` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK5jgwecmfn1vyn9jtld3o64v4x`(`category_id`) USING BTREE,
  CONSTRAINT `FK5jgwecmfn1vyn9jtld3o64v4x` FOREIGN KEY (`category_id`) REFERENCES `book_category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (2, '马伯庸', 'images/2.jpg', '2024-05-16 18:06:31.000000', b'0', '9787572608582', 45.5, '湖南文艺出版社', '大唐天宝十四年，长安城的小吏李善德突然接到一个任务：要在贵妃诞日之前，从岭南运来新鲜荔枝。荔枝“一日色变，两日香变，三日味变”，而岭南距长安五千余里，山水迢迢，这是个不可能完成的任务，可为了家人，李善德决心放手一搏：“就算失败，我也想知道，自己倒在距离终点多远的地方。”\r\n《长安的荔枝》是马伯庸备受好评的历史小说。\r\n唐朝诗人杜牧的一句“一骑红尘妃子笑，无人知是荔枝来”一千多年来引发了人们的无限遐想，但鲜荔枝的保鲜时限仅有三天，这场跨越五千余里的传奇转运之旅究竟是如何达成的，谁让杨贵妃在长安吃到了来自岭南的鲜荔枝？作者马伯庸就此展开了一场脑洞非常大的想象。\r\n沿袭马伯庸写作一贯以来的时空紧张感，不仅让读者看到了小人物的乱世生存之道，也感受到了事在人为的热血奋斗。随书附赠“荔枝鲜转运舆图”。\r\n★ 编辑推荐\r\n唐朝诗人杜牧的一句“一骑红尘妃子笑，无人知是荔枝来”惹得世人艳羡杨贵妃上千年，但其中的荔枝是如何从五千余里外的岭南运送到长安城的，却鲜有史书详细记载，脑洞大开的马伯庸以此为蓝本构建了一个大唐社畜李善德拼尽全力做项目的故事，虽是历史小说，读者却能从中看到自己的生活影子，大城市买房落脚、职场情商博弈、不得已的违规逾矩等，小人物的挣扎是那么相似。一项将鲜荔枝运逾千里之距的艰难差事，以微观人事折射大唐宏观社会。\r\n这部口碑非常好的历史小说只花了11天写就，小说刚一连载就获得了广大读者的好评，被数万人点评为神作，推荐值高达96%。微博、抖音、小红书和今日头条，海量读者自发评论和衍生二创。\r\n本书小开本双封设计，随书附赠“荔枝鲜转运舆图”折页插图一张。\r\n★ 媒体推荐\r\n马伯庸把他对历史的熟稔与现实关怀结合在一起，使得文笔能直击人的内心。写的是古人，却经常让我们看到自己。这部《长安的荔枝》就是如此。\r\n——陕西师范大学历史文化学院教授 于赓哲', '长安的荔枝', 15, '2024-05-01', b'1', 1);
INSERT INTO `book` VALUES (6, '马伯庸', 'images/6.jpg', '2024-05-16 18:06:34.000000', b'0', '9787572608582', 45.5, '湖南文艺出版社', '太白金星李长庚最近有点烦。\r\n天庭和西天联合推出了“西天取经”的重大项目，他受命策划九九八十一难，确保唐僧能安全走完流程，平稳取经成佛。老神仙本以为一切尽在掌控中，谁知天大的麻烦才刚刚开始：费用报销、工作汇报、人事安排、各路大仙塞来的条子、各地妖怪暗藏的心思，捋不出的千头万缕，做不完的琐碎繁杂……当大闹天宫的真相重新浮出水面，牵扯出无数因果，李长庚发觉自己成就金仙的道路越加渺茫。\r\n【编辑推荐】\r\n这是每一个成年人应该重读的西游故事。\r\n太白金星李长庚最近有点烦。天庭和西天联合推出了“西天取经”的重大项目，他受命策划九九八十一难，确保项目无虞。老神仙本以为一切尽在掌控中，谁知天大的麻烦才刚刚开始。\r\n项目过程中，如何报销活动费用？如何写工作汇报？如何平衡不同上级的诉求？如何应对突发状况？如何解决人事纠纷？一系列问题在不改变经典著作《西游记》结局的基础上展开了丰富有趣的职场生态，即便李长庚锦囊无数，也不免常常陷入焦虑。\r\n都说神仙要“超脱因果，太上忘情”，都说不要在职场里动感情，太白金星却说，“很多人间执念我们无法理解，但不代表那些痛苦就不存在。”即使得了“仙”“佛”之号，依旧抛不下一个人字，有人，就有抛不下的牵挂、因果与苦乐得失。\r\n打碎无意义的追逐，才是真的得道。\r\n延续《长安的荔枝》小开本双封设计，随书附10张著名画家施晓颉所绘创意插图。\r\n【媒体推荐】\r\n地下取经队伍降妖除魔百般威武，天上神界仙界各怀心思斗智斗勇。现代视角另类演绎西游故事，就算成仙也得讲究职场智慧。\r\n——《收获》杂志', '太白金星有点烦', 15, '2024-05-01', b'1', 5);
INSERT INTO `book` VALUES (24, '黄灯', 'images/24.jpg', '2024-05-15 18:06:37.000000', b'0', '9787020184262', 10, '人民文学出版社', '《去家访》是黄灯继《我的二本学生》之后推出的新作，记录了她在2017年-2022年走访自己学生原生家庭的所见、所闻、所感、所想。在这些散落在地图上、需要无限放大才能看到它名字的小城、乡镇、村落里，黄灯与学生的父母、祖父祖母、兄弟姐妹、同学发小、街坊邻居一起交流，倾听他们对教育和人生的体悟，进而更真切和深入地了解那些从四面八方来到她课堂上的年轻人，她的二本学生。\r\n从讲台上走下来，黄灯跟随学生回家的路线，一路换乘高铁、长途客车、中巴车，电动车、摩托车来到腾冲、郁南、阳春、台山、怀宁、东莞、陆丰、普宁、佛山、深圳、饶平、湛江、遂溪、廉江、韶关、孝感等地，来到已经废弃的小学操场、爬上老房子的屋顶、坐在茶园的高坡上、溜进快递分装车间、穿梭于养蚝厂的水域间、捡起田埂上红薯枝叶的藤蔓，来感受学生成长的环境，体验每一个家庭为孩子教育所做的艰辛付出。在这一遍又一遍脚踏实地的走访中，黄灯既贴近了自己的学生，也贴近了家长，并在更深的意义上贴近了自己、贴近了教育、贴近了当下中国的现实。没有什么是易得的，哪怕是二本院校，也需要孩子全力以赴，和家庭倾力托举。\r\n黄灯在《去家访》中延续了她在《我的二本学生》创作中的真诚和恳切，这是对生命个体的真诚探究，也是对文字本身的真诚。她不远千里去到学生家中，同吃同住同行，这是对学生成长的恳切关系，也是对放下年轻人这个群体未来的恳切关心。', '去家访', 4, '2024-02-01', b'1', 3);
INSERT INTO `book` VALUES (27, '刘慈欣', 'images/27.jpg', '2024-05-17 19:09:32.386000', b'0', '9787536693968', 0, '重庆出版社', '三体人在利用魔法般的科技锁死了地球人的科学之后，庞大的宇宙舰队杀气腾腾地直扑太阳系，意欲清除地球文明。\r\n面对前所未有的危局，经历过无数磨难的地球人组建起同样庞大的太空舰队，同时，利用三体人思维透明的致命缺陷，制订了神秘莫测的“面壁计划”，精选出四位“面壁者”。秘密展开对三体人的反击。\r\n三体人自身虽然无法识破人类的诡谲计谋，却依靠由地球人中的背叛者挑选出的“破壁人”，与“面壁者”展开智慧博弈……\r\n“面壁计划”究竟能否成功？地球人究竟能否在这场你死我活的文明生存竞争中战而胜之？神秘的\r\n“黑暗森林”究竟意味着什么？', '三体Ⅱ', 15, '2008-05-01', b'1', 3);
INSERT INTO `book` VALUES (28, '[英] J·K·罗琳', 'images/28.jpg', '2024-05-17 19:17:27.208000', b'0', '9787020086627', 430, '人民文学出版社', '《哈利·波特》系列小说被翻译成七十多种语言，在全世界两百多个国家累计销量达四亿多册，位列史上非宗教、市场销售类图书首位。《哈利·波特精装全集（套装全7册）（新版）》其中前六部以霍格沃茨魔法学校为主要舞台，描写的是主人公哈利·波特在霍格沃茨魔法学校六年的学习生活冒险故事。第七本描写的是哈利·波特在野外寻找魂器并消灭伏地魔的故事。这个人物叱咤文学江湖，让数不清的读者为之倾倒，这不能不说是文学史上的一个奇迹。罗琳这个富有想象力的魔法妈妈带给了无数人欢笑与泪水，更带给了全世界的哈迷一个美丽的梦。《哈利·波特》以其小说的闻名，带动了一系列相关文化内容的开发，包括电影，游戏，道具，周边玩具，系列景点，系列游园，游乐园，相关书籍，相关主题城市等。', '哈利·波特精装全集（套装全7册）', 18, '2011-01-01', b'1', 2);
INSERT INTO `book` VALUES (29, '[英] 查尔斯·弗里曼', 'images/29.jpg', '2024-05-17 19:23:19.043000', b'0', '9787513928960', 169, '民主与建设出版社', '牛津大学出版社20年修订3版，英语世界古代地中海文明通史标准读本\r\n融最新考古发现、文献解读与游记笔调于一体\r\n政治、军事、文化、艺术多角度立体呈现\r\n88幅精美插图、20幅详细地图、13幅建筑平面图，生动展现5000年西方古代世界壮美画卷！\r\n◎ 编辑推荐\r\n☆本书自牛津大学出版社1996年首次出版后受到了广大师生以及普通读者的好评，至2014年第三版出版经历了两次全面修订，补充了近20年间古代史领域的最新考古发现和研究成果，始终是古代文明一本全的最佳选择\r\n☆作者曾广泛游历地中海沿岸各古代遗址，以旅行家的笔调将古代历史娓娓道来\r\n☆书中共有88幅精美插图、20幅详细地图、13幅建筑平面图，帮助读者在视觉层面对古代世界有直接的了解\r\n◎ 内容简介\r\n本书是一部讲述古代地中海诸文明兴衰、探讨西方文明起源与形成的地中海古代通史。作者查尔斯·弗里曼以埃及、希腊与罗马这三个最具代表性的地中海古代文明作为切入点，带领读者全面回顾了自公元前5000年古代近东文明勃兴，至公元600年古典时代结束为止，整个地中海世界跌宕起伏的历史。本书超越了传统的政治史框架，力图通过文学、艺术、哲学、建筑以及社会经济等多种维度，全方位地介绍与解释历史。作者还用大量篇幅回顾了近代以来欧洲人重新发现与接受希腊-罗马文明的历程，提醒读者警惕对于历史的滥用。书中另附有多幅精美的插图与地图。\r\n本书虽然是一部古代历史的入门级读物，但书中仍不乏严谨的学术讨论和严肃的思考，并用浅显的文字为读者呈现了西方学术界近年来的最新研究成果与重大考古发现。\r\n◎ 名人推荐\r\n查尔斯·弗里曼是我最为推崇的一位历史学者，他一直将历史置于广阔的地理与时代背景下加以审视《埃及、希腊与罗马》是一本永远值得一读的佳作，为那些对历史充满好奇的读者提供了一个全方位了解和认知古代地中海诸文明的机会。 ——保罗·卡特利奇，剑桥大学教授\r\n这本介绍古代地中海文明的著作是一个了不起的成就……这本书的语言简洁而生动，尤其适合一般读者以及那些有志于历史研究者作为入门读物。无论就哪个角度而言，弗里曼的这本书都是一部经受得起时间考验的经典之作。 ——阿兰·劳埃德，埃及探索协会主席\r\n本书对地中海三大古代文明——埃及、希腊与罗马文明的介绍与分析切中肯綮、一针见血。查尔斯·弗里曼出色地完成了自己的任务。 ——理查德·迈尔斯，悉尼大学教授', '埃及、希腊与罗马', 6, '2022-07-07', b'1', 3);
INSERT INTO `book` VALUES (30, '亦舒', 'images/30.jpg', '2024-05-18 17:01:15.877000', b'0', '9787540481759', 38, '湖南文艺出版社', '一次从2030回到1985时空旅行，她见到了5岁的母亲，也见到了方中信，那个不得不遇到的人。 她第一次体会到她的时代被她忽略的人间温情；他告诉她“生命只需好，无需长”。\r\n一段根本无法在历史和时空中存活的感情. “我是不得不回来，我是不得不走，我们是不得不拆散”。 当一切即将真相大白，那包用印满玫瑰花的盒子精心保护的巧克力和墓碑上的一行小字，让她在那个空旷的墓园里，嚎啕大哭，泪如雨下，不能自抑。\r\n“你知不知道巧克力最神秘之处在什么地方？ 让我告诉你， 巧克力含一种化学分子， 当人堕入情网， 他的脑子会分泌同样的分子。”', '朝花夕拾', 15, '2017-07-10', b'1', 3);
INSERT INTO `book` VALUES (31, '[日] 叶真中显', 'images/31.jpg', '2024-05-28 17:27:45.488000', b'0', '9787559641182', 10, '北京联合出版公司', '罗翔老师推荐！“这本书深刻地揭示了日本的社会问题，日本40多年来社会变迁的各种缩影。”\r\n豆瓣「2020年度读书榜单」推理·悬疑类top1！\r\n怀才者的自怜是天鹅的挽歌， 平庸者的崩坏是野兽的绝叫。\r\n-\r\n★同名日剧位居豆瓣“2019评分最高日本剧集”榜单Top10\r\n★日本现象级社会派推理小说\r\n★长达四十年的恶女编年史\r\n★绝对现实，却超乎想象！\r\n-\r\n【内容简介】\r\n铃木阳子死了，死在独居的公寓里。正确说来，是铃木阳 子几个月前死了。因为发现她时，不但遗体遭到屋内的十一只猫啃食，连猫也全数饿死了。铃木阳子显然是「孤独死」的最佳范例，但这名女子为何落到这步田地？她的亲人、朋友、同事在哪里？她的人生轨迹又是如何？\r\n-\r\n【编辑推荐】\r\n《绝叫》是日本新锐社会派推理作家叶真中显创作的推理小说，讲述了女主人公阳子在平庸懦弱的表象之下，隐密的跌宕、罪恶人生。该书揭露常被人们忽视的社会问题，深刻而引人深思，刻画人物思想的变化细腻而一针见血，以阳子的离奇经历，连缀了日本四十年来社会变迁的各种缩影，是日本近年来具有代表性的优秀推理小说。\r\n该书在日本一经出版就引起极大反响，入围第68回日本推理作家协会奖、第36回吉川英治文学新人奖、第27回“这本推理小说了不起”大奖 、第12回书店大奖、第11回启文堂大奖、周刊文春年度推理小说榜。\r\n后《绝叫》繁体版问世，使国内推理小说爱好者得以一探究竟。《绝叫》繁体版在豆瓣获得8.6的高评分，是少有的在有简体中文版之前就广受好评的外国小说。\r\n2019年，根据《绝叫》改编的同名日剧播出，又获得了豆瓣逾万人点评、8.4分的好成绩，并入围豆瓣“2019评分最高日本剧集”榜单Top10。\r\n2020年7月，《绝叫》简体中文版正式上市，以飨读者。', '绝叫', 15, '2024-05-16', b'1', 1);

-- ----------------------------
-- Table structure for book_category
-- ----------------------------
DROP TABLE IF EXISTS `book_category`;
CREATE TABLE `book_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_category_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK1mn8vv77a4ace2rpujcx1qeaq`(`parent_category_id`) USING BTREE,
  CONSTRAINT `FK1mn8vv77a4ace2rpujcx1qeaq` FOREIGN KEY (`parent_category_id`) REFERENCES `book_category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_category
-- ----------------------------
INSERT INTO `book_category` VALUES (1, '哲学、宗教', NULL);
INSERT INTO `book_category` VALUES (2, '军事', NULL);
INSERT INTO `book_category` VALUES (3, '经济', NULL);
INSERT INTO `book_category` VALUES (4, '文学', NULL);
INSERT INTO `book_category` VALUES (5, '艺术', NULL);
INSERT INTO `book_category` VALUES (6, '历史', NULL);
INSERT INTO `book_category` VALUES (7, '地理', NULL);
INSERT INTO `book_category` VALUES (8, '中国哲学', 1);
INSERT INTO `book_category` VALUES (9, '古代哲学', 8);
INSERT INTO `book_category` VALUES (10, '外国哲学', 1);
INSERT INTO `book_category` VALUES (12, '近代哲学', 8);
INSERT INTO `book_category` VALUES (13, '中国经济', 3);
INSERT INTO `book_category` VALUES (14, '中国文学', 4);
INSERT INTO `book_category` VALUES (15, '小说', 14);
INSERT INTO `book_category` VALUES (16, '科技', NULL);
INSERT INTO `book_category` VALUES (17, '外国文学', 4);
INSERT INTO `book_category` VALUES (18, '魔幻', 17);
INSERT INTO `book_category` VALUES (19, '音乐', NULL);
INSERT INTO `book_category` VALUES (20, '教育', NULL);
INSERT INTO `book_category` VALUES (21, '文学1', 4);

-- ----------------------------
-- Table structure for punish
-- ----------------------------
DROP TABLE IF EXISTS `punish`;
CREATE TABLE `punish`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `money` double NOT NULL,
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `record_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK8rhyph0pxocismw1osfcx900h`(`record_id`) USING BTREE,
  CONSTRAINT `FK8rhyph0pxocismw1osfcx900h` FOREIGN KEY (`record_id`) REFERENCES `record` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of punish
-- ----------------------------
INSERT INTO `punish` VALUES (2, '2024-05-17 21:21:11.286000', 100000, '不还书，', 7);
INSERT INTO `punish` VALUES (3, '2024-05-18 19:02:58.775000', 32, 'time out', 14);
INSERT INTO `punish` VALUES (4, '2024-05-27 17:29:11.390000', 100, '还书超时', 11);
INSERT INTO `punish` VALUES (5, '2024-05-28 17:34:05.371000', 1000, '超时', 12);
INSERT INTO `punish` VALUES (6, '2024-06-01 13:56:10.346000', 100, '超时未还', 20);

-- ----------------------------
-- Table structure for reader
-- ----------------------------
DROP TABLE IF EXISTS `reader`;
CREATE TABLE `reader`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `card_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `college` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `grade` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `graduation` tinyblob NULL,
  `major` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `student_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `valid` tinyblob NULL,
  `borrow_count` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reader
-- ----------------------------
INSERT INTO `reader` VALUES (1, '544023230023', '信息工程与自动化学院（人工智能产业学院）', '2024-05-16 18:06:48.000000', '2021', 0x30, '计算机科学与技术', '张三', '15770289601', '2021104035', 0x31, 5);
INSERT INTO `reader` VALUES (3, '5440233223', '信息工程与自动化学院（人工智能产业学院）', '2024-05-15 18:06:51.000000', '2021', 0x30, '化学', '李四', '15770289688', '2021104027', 0x31, 3);
INSERT INTO `reader` VALUES (4, '5440233324', '环境科学与工程学院', '2024-05-16 18:11:17.600000', '2021', 0x30, '环境部', '老王', '172832323', '2021104030', 0x31, 4);
INSERT INTO `reader` VALUES (5, '53320320020302032', '信息工程与自动化学院（人工智能产业学院）', '2024-05-17 19:27:06.833000', '2021', 0x30, '计算机科学与技术', '段国磊', '15770289600', '202110405327', 0x31, 9);

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `actual_return_date` datetime(6) NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `is_overdue` bit(1) NOT NULL,
  `is_return` bit(1) NOT NULL,
  `return_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `book_id` bigint(20) NULL DEFAULT NULL,
  `reader_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKbcjk1br3shw5ke3ry9lfiqwgp`(`book_id`) USING BTREE,
  INDEX `FKd40y3x5j62st2u534mgrtwpro`(`reader_id`) USING BTREE,
  CONSTRAINT `FKbcjk1br3shw5ke3ry9lfiqwgp` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKd40y3x5j62st2u534mgrtwpro` FOREIGN KEY (`reader_id`) REFERENCES `reader` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES (2, '2024-05-17 21:01:33.790000', '2024-05-15 19:34:35.000000', b'0', b'1', '2024-05-19', 6, 5);
INSERT INTO `record` VALUES (4, '2024-05-18 14:43:11.428000', '2024-05-17 19:35:19.869000', b'0', b'1', '2024-05-30', 27, 1);
INSERT INTO `record` VALUES (5, '2024-05-17 19:42:19.163000', '2024-05-14 19:35:30.000000', b'0', b'1', '2024-05-30', 28, 4);
INSERT INTO `record` VALUES (6, '2024-05-18 14:43:07.638000', '2024-05-14 21:01:50.000000', b'0', b'1', '2024-05-21', 29, 4);
INSERT INTO `record` VALUES (7, '2024-05-18 14:43:02.492000', '2024-05-17 21:20:41.113000', b'1', b'1', '2024-05-16', 6, 4);
INSERT INTO `record` VALUES (8, '2024-05-18 17:01:52.894000', '2024-05-18 14:43:37.166000', b'0', b'1', '2024-05-22', 27, 5);
INSERT INTO `record` VALUES (9, '2024-05-18 17:01:55.106000', '2024-05-18 14:46:54.645000', b'0', b'1', '2024-05-20', 2, 5);
INSERT INTO `record` VALUES (10, '2024-05-18 17:54:45.753000', '2024-05-18 17:02:10.860000', b'0', b'1', '2024-05-22', 30, 5);
INSERT INTO `record` VALUES (11, '2024-05-19 11:54:56.757000', '2024-05-18 17:51:32.219000', b'1', b'1', '2024-05-18', 24, 4);
INSERT INTO `record` VALUES (12, '2024-05-27 16:41:10.934000', '2024-05-18 17:52:19.917000', b'1', b'1', '2024-05-22', 27, 3);
INSERT INTO `record` VALUES (13, '2024-05-21 14:48:30.920000', '2024-05-18 17:54:29.352000', b'0', b'1', '2024-05-23', 6, 5);
INSERT INTO `record` VALUES (14, '2024-05-19 11:54:54.526000', '2024-05-18 17:55:22.844000', b'1', b'1', '2024-05-17', 29, 1);
INSERT INTO `record` VALUES (15, '2024-05-21 14:07:43.825000', '2024-05-19 16:03:13.971000', b'0', b'1', '2024-05-23', 24, 5);
INSERT INTO `record` VALUES (16, '2024-05-21 14:07:41.426000', '2024-05-19 16:24:20.322000', b'0', b'1', '2024-05-22', 29, 3);
INSERT INTO `record` VALUES (17, '2024-05-23 16:41:06.000000', '2024-05-21 14:08:15.073000', b'0', b'1', '2024-05-24', 30, 5);
INSERT INTO `record` VALUES (18, '2024-05-23 16:41:02.000000', '2024-05-21 14:48:41.468000', b'0', b'1', '2024-05-24', 6, 5);
INSERT INTO `record` VALUES (20, '2024-06-01 13:43:07.520000', '2024-05-27 17:23:53.810000', b'1', b'1', '2024-05-30', 30, 5);
INSERT INTO `record` VALUES (21, '2024-05-28 17:33:38.309000', '2024-05-27 17:24:08.025000', b'0', b'1', '2024-05-30', 6, 1);
INSERT INTO `record` VALUES (22, '2024-05-28 16:16:35.865000', '2024-05-28 16:08:55.764000', b'0', b'1', '2024-05-31', 28, 1);
INSERT INTO `record` VALUES (23, '2024-05-28 17:38:29.866000', '2024-05-28 17:33:23.075000', b'0', b'1', '2024-05-31', 31, 3);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '2024-05-16 18:06:22.000000', '3197083782@qq.com', '1', 'admin');
INSERT INTO `user` VALUES (2, '2024-05-16 18:06:13.109000', 'jdvh31@163.com', '1', 'dgl');

SET FOREIGN_KEY_CHECKS = 1;
