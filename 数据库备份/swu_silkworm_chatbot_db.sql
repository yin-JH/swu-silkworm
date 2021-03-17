/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.18 : Database - swu_silkworm_chatbot_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`swu_silkworm_chatbot_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `swu_silkworm_chatbot_db`;

/*Table structure for table `account` */

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_name` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `account_password` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `account` */

insert  into `account`(`id`,`account_name`,`account_password`) values (1,'admin','123456');

/*Table structure for table `question` */

DROP TABLE IF EXISTS `question`;

CREATE TABLE `question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `flag` tinyint(255) NOT NULL DEFAULT '1' COMMENT '该条目的标志，0表示不可用，等同于删除记录',
  `problem` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `keywords` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '关键词是问题切分出来的关键词，关键词之间使用“|”来分割',
  `type` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '这是问题的分类',
  `media_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '这是回答的媒体类型',
  `answer` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=182 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `question` */

insert  into `question`(`id`,`flag`,`problem`,`keywords`,`type`,`media_type`,`answer`) values (2,0,'蚕卵需要光照吗','蚕卵|需要|光照|','孵化','text','蚕卵感光后会发育得更整齐，蚕卵放室内，不可放阳光下直接照射。'),(3,1,'蚕卵感光可以开灯照吗','蚕卵|感光|可以|开灯|照|','孵化','text','不用刻意开灯照，室内正常日光即可。'),(4,0,'蚕是昆虫吗','蚕|昆虫|','特点','text','是的，蚕宝属于动物界、 节肢动物门 、昆虫纲、 鳞翅目、蚕蛾科、蚕蛾属、家蚕种 。'),(5,1,'昆虫的特点是什么','昆虫|特点|','特点','text','昆虫 身体分为头、胸、腹三部分。幼虫在生长过程中会蜕皮不断大。'),(6,1,'蚕一生分为哪几个阶段','蚕|一生|分为|阶段|','特点','text','蚕宝一生要经历 蚕宝一生要经历 卵、幼虫、蛹 和蛾四个阶段 ，是完全变态昆虫 。'),(7,1,'为什么蚕要蜕皮','蚕|要|蜕皮|','生长发育','text','蚕宝幼虫拥有由几丁质构成的表皮，到了一定阶段表皮就会束缚它的生长，需要蜕掉旧的表皮，换上新的表皮。'),(8,1,'蚕蜕皮时有什么特征','蚕|蜕|皮时|特征|','生长发育','text','蚕宝一生要蜕4次皮，每蜕皮一次龄期就增加一龄，所以共有5个龄期。蚕宝蜕皮时不吃不动，需要休息大约1天时间。小朋友千万不要打扰正在蜕皮的蚕宝宝，可能会导致蚕宝无法蜕皮而死掉。'),(9,1,'眠期是什么','眠期|','生长发育','text','蚕宝蜕皮时不吃不动，需要休息大约1天时间，这个时期就像是在睡觉，所以叫眠期。'),(10,1,'蚕生长温度是多少','蚕|生长|温度|','生长发育','text','蚕宝生长最舒适的温度是23-29℃, 上下5℃也凑合；不过低于10℃或者高于35℃的温度就严重啦，会引起蚕昏迷甚至致死。'),(11,1,'3龄蚕有多大','3|龄|蚕有多大|','生长发育','text','大概1-2cm长 '),(12,1,'幼虫眠的时候特征','幼虫|眠|时候|特征|','生长发育','text','头顶有浅黄色的倒三角，不吃不动，不能触碰'),(13,1,'蚁蚕吃什么样的桑叶','蚁蚕|吃|桑叶|','生长发育','text','嫩黄色，桑树顶端的叶子'),(14,1,'5龄蚕吃什么样的桑叶','5|龄|蚕|吃|桑叶|','生长发育','text','墨绿色，看起来汁水丰沛的叶子'),(15,1,'化蛹需要几天','化蛹|需要|','生长发育','text','从幼虫到蛹大概需要2-3天。'),(16,1,'蛹期到蛾子多久','到|蛾子|','生长发育','text','一般是10天'),(17,1,'化性是什么','化|性|','生长发育','text','蚕在一年内自然发生的世代数，3代以上为多化性。'),(18,1,'蚕蜕皮过程中是什么激素在起作用','蚕|蜕皮|过程|激素|起|作用|','生长发育','text','蚕蜕皮过程中主要是蚕体内咽侧体分泌的保幼激素和前胸腺分泌的蜕皮激素共同起作用。'),(19,1,'蛹期的雌雄蚕鉴别','雌雄蚕|鉴别|','特点','text','雌蛹腹部肥大，第8腹节腹面正中线上，有一条纵线。雄蛹第8，9腹节腹面正中线的交界处有一小点。'),(20,1,'雌蛾的诱惑腺所释放出的性信息激素是什么','雌|蛾|诱惑|腺|释放|出|性|信息|激素|','生长发育','text','性信息激素主要是由桑蚕醇和桑蚕醛所组成。'),(21,1,'蚕卵是否滞育的取决因素','蚕卵|是否|滞|育|取决|因素|','生长发育','text','蚕卵是否滞育取决于蛹期中雌蛹的咽下神经节能否分泌滞育激素，心侧体激素也有辅助作用'),(22,1,'雌雄蛾腹部体节的差异','雌雄|蛾|腹部|体节|差异|','特点','text','雌蛾腹部7节，雄蛾8节'),(23,1,'正常卵与死卵的差异','卵|死|卵|差异|','特点','text','正常卵的卵涡为椭圆形，死卵呈三角形。'),(24,1,'雌雄蚕的鉴别','雌雄蚕|鉴别|','特点','text','雄蚕在第9腹节的腹面前缘中央有一个椭圆形的乳白色的小点，为赫氏腺，雌蚕在蚕的尾部第8，9腹节腹面，左右对称地各有一对乳白色的小圆点，为石渡氏腺。'),(25,1,'蚕的足的分布情况','蚕|分布|情况|','特点','text','蚕的胸部分布有3对胸足，腹部分布有4对腹足，1对尾足，共8对。'),(26,1,'蚕的生长发育情况','蚕|生长|发育|情况|','生长发育','text','蚕在产卵后10-12天后破茧，孵化3-4天后脱壳，经过4次蜕皮15.5-19天后成为成虫。'),(27,1,'蚕运动是怎么进行的','蚕|运动|进行|','特点','text','四对腹足和一对尾足，胸足不参与躯体运动'),(28,1,'蚕从幼虫变成蚕蛾的过程叫什么？这类昆虫叫','蚕|幼虫|变成|蚕蛾|过程|叫|昆虫|叫|','生长发育','text','变态发育，变态昆虫'),(29,1,'蚕丝的两大主要成分','蚕丝|两大|主要|成分|','应用','text','丝胶，丝素'),(30,1,'蚕丝的应用场景有哪些','蚕丝|应用|场景|','应用','text','化妆品，丝织品，高强度纤维，安全敷料等等'),(31,1,'为什么蚕吐的丝有各种各样的颜色','蚕|吐|颜色|','应用','text','桑叶中的色素物质蚕不能完全消化吸收，不同品种对不同色素的吸收能力不同，所以产生不同颜色的蚕丝'),(32,1,'蚕一般是什么颜色','蚕|颜色|','特点','text','家蚕宝宝一般是白色，而野蚕宝宝一般是棕黄色喔'),(33,1,'蚕一般产一次卵能产多少枚卵','蚕|产|一|卵|能|产|卵|','特点','text','蚕宝宝一口气可以产400～500枚卵喔'),(34,1,'蚕怎么分雌雄','蚕|雌雄|','特点','text','蚕宝宝腹部有透明小点喔，有四个小点的是雌蚕宝宝，有一个小点的雄蚕宝宝喔'),(35,1,'蚕有几个体节','蚕|个体|节|','特点','text','蚕的身体分为头、胸、腹三部分,共十三节构成。'),(36,1,'蚕有几个龄期','蚕|龄|','生长发育','text','蚕宝宝一共有五个龄期(一龄，二龄，三龄，四龄，五龄)，七个阶段(除五龄外还有蛹期和蛾期喔)'),(37,1,'蚕一生蜕几次皮','蚕|一生|蜕|皮|','生长发育','text','蚕宝宝一生要蜕四皮喔'),(38,1,'蚕一生能生存多久','蚕|一生|能|生存|','生长发育','text','蚕一生能生存41～51天'),(39,1,'蚕茧只有白色的吗','蚕茧|白色|','特点','text','蚕茧有多种颜色，比较常见的有白色，浅黄色，金黄色，偶尔还会有粉红色，身体是黑色的虎蚕做出来的茧就是金黄色。'),(40,1,'雄蚕吐丝多还是雌蚕吐丝多啊','雄蚕|吐丝|多|雌蚕|吐丝|多|','特点','text','雄蚕吐丝更多喔'),(41,1,'刚孵出来的蚕别称什么','孵|出来|蚕|别称|','生长发育','text','刚孵出来的蚕宝宝黑黑的，小小的，像蚂蚁一样别称“蚁蚕”'),(42,1,'在蚕眠期的时候可以把它挪来挪去吗','蚕眠|时候|可以|挪|来|挪|去|','生长发育','text','不能，蚕在眠期的时候会吐丝将自己固定在一个地方以便蜕皮，挪来挪去会影响蜕皮'),(43,1,'蚕对人类的用途','蚕|人类|用途|','应用','text','蚕蛹可以吃，吐的丝可以做衣服、面膜、材料，蚕砂可以做枕头芯，入药等等'),(44,1,'小蚕要如何饲养','小蚕|要|饲养|','养殖','text','养蚕前做好蚕具消毒工作，注意养蚕的通风和除砂，'),(45,1,'熟蚕何时上蔟','熟蚕|上蔟|','生长发育','text','蚕5龄饷食后，经6—8天，食桑渐减，体色由青白色转为腊黄色，排软粪，随后停止食桑，排出大量绿色软粪，胸部变透明，头抬高频频摆动寻找结茧位置，这时就要及时捉蚕或引蚕上蔟。'),(46,1,'怎样除沙','除沙|','养殖','text','小蚕期除沙次数不宜过多，一般一龄眠除一次，如蚕砂不厚最好不除。二龄起、眠各除一次，三龄起、中、眠各除一次。'),(47,1,'小蚕期蚕的发育特点和生理要求是什么','小蚕期|蚕|发育|特点|生理|要求|','生长发育','text','1—3龄期小蚕对高温多湿适应性强，对冷湿抵抗力弱；生长发育快，对桑叶质量要求严格，对二氧化碳抵抗力强，对有毒气体抵抗力弱，对病毒的抵抗力弱，尤其要注意消毒。'),(48,1,'大蚕期的特点是什么','大蚕期|特点|','生长发育','text','大蚕期蚕对高温多湿，闷热环境的抵抗力弱，特别是5龄期抵抗力最弱 ，如遇高温多湿环境，蚕体容易积热、积湿诱发蚕病，应在适宜温度干燥的环境下饲养。'),(49,1,'大蚕小蚕可以一起养吗','大蚕|小蚕|可以|养|','养殖','text','不可以，它们吃的桑叶不一样，适宜生长环境也不一样，而且大蚕会把小蚕压到不利于小蚕生长发育'),(50,1,'蚕从卵变成虫的周期是多长','蚕|卵|变|成虫|周期|多|','孵化','text','一般一只蚕的生命周期在50天左右'),(51,1,'蚕从卵变成虫需要经过几个龄期','蚕|卵|变|成虫|需要|龄|','孵化','text','5个龄期，分别是1龄、2龄、3龄、4龄、5龄。'),(52,1,'蚕只吃桑叶吗','蚕|吃|桑叶|','生长发育','text','蚕的主食是桑叶，可少量食用其他植物的叶子，也可以吃蚕饲料等'),(53,1,'蚕的成虫是蝴蝶吗','蚕|成虫|蝴蝶|','孵化','text','不是，毛毛虫的成虫是蝴蝶，蚕的成虫是蛾子'),(54,1,'蚕有牙齿吗','蚕|牙齿|','生长发育','text','蚕是有牙齿的，可用来破碎植物叶子'),(55,1,'蚕为什么要吐丝','蚕|要|吐丝|','生长发育','text','处于成熟期的蚕，丝腺体的丝液出来与空气接触就变成了丝'),(56,1,'蚕结茧后几天后会成为蛹呢','蚕|结|茧|几天后|成为|','生长发育','text','一般为12-15天左右'),(57,1,'蚕怎么繁殖','蚕|繁殖|','繁殖','text','蚕蛹化后变成虫，成虫会产卵，卵会孵化成蚕，如此循环'),(58,1,'蚕为什么最爱桑叶','蚕|爱|桑叶|','生长发育','text','因为桑叶中含有对蚕有吸引的引诱物质'),(59,1,'如何区分蚕处于什么龄期','区分|蚕|处于|龄|','生长发育','text','蚕的龄期主要靠它眠期区分，每经过一个眠期就会增长一龄'),(60,1,'饲料养蚕怎么收蚁','饲料|养蚕|收蚁|','生长过程','text','饲料不宜太干，也不宜太湿，太干蚕宝宝不能吃，太湿会爬不动，在饲料里层缺氧而死。饲料应该切成薄薄的一层，用软软的刷子把蚁蚕刷到饲料上。'),(61,1,'饲料养蚕怎么除蚕沙','饲料|养蚕|蚕沙|','生长过程','text','应该每天坚持除沙，可以每天把新的饲料放在原来饲料的旁边，等蚕宝宝都爬到新饲料上后，再把旧饲料扔掉。'),(62,1,'饲料养蚕在眠期怎么弄','饲料|养蚕|眠期|弄|','生长过程','text','应该把盖子打开，里面保持干燥。因为太湿不利于蚕宝宝脱皮'),(63,1,'饲料养蚕怎么做到整齐划一','饲料|养蚕|怎么做|到|','生长过程','text','因为每天都除沙，眠期时打开盖子，把饲料风干，等大多数都眠起时，再放新饲料，同时排除哪些久久未起的蚕宝宝。'),(64,1,'饲料蚕跟桑叶蚕差别大吗','饲料|蚕|桑叶|蚕|差别|','生长发育','text','不大，只有养的好，差别不是很明显。'),(65,1,'为什么饲料养蚕的蚕沙不明显','饲料|养蚕|蚕沙|','生长过程','text','饲料养的蚕(特别是小龄蚕)的蚕沙颜色跟饲料的颜色很相近，才会分不清，等蚕宝长大了就很容易区分了。'),(66,1,'有几个眠期，眠多久','眠期|眠|','生长发育','text','现在多数为4眠蚕，一眠约1天，二眠约大半天，三眠约1天，四眠1天半左右'),(67,1,'眠期特征，注意事项','眠期|特征|注意|事项|','生长发育','text','不吃东西了，头胸部扬起不动，有丝把脚牢牢抓住底部，身体有点发白。眠期的蚕最好勿动。'),(68,1,'饲料养蚕还吃桑叶吗','饲料|养蚕|吃|桑叶|','饲料','text','饲料养的蚕是可以吃桑叶的，但尽量不要用桑叶喂蚕宝哦。'),(69,1,'桑叶养的蚕还吃饲料吗','桑叶养|蚕|吃|饲料|','生长发育','text','用桑叶养过的蚕，就不会吃饲料了'),(70,1,'蚕除了吃桑叶还可以吃其他叶子吗','蚕|吃|桑叶|可以|吃|叶子|','生长发育','text','蚕是靠嗅觉和味觉器官来辨认桑叶气味的，最爱吃桑叶，除了桑叶外还有柘叶、榆叶等'),(71,1,'蚕宝宝的丝是从哪里吐出来的呢','蚕宝宝|吐|出来|','生长发育','text','丝是从口器吐出来的噢，它摆动头部，将丝吐出来缠绕成茧。'),(72,1,'蚕宝宝的丝有什么用途呢','蚕宝宝|丝有|用途|','生长发育','text','蚕宝宝吐丝结茧保护自己。蚕茧也可以缫丝用于服装、衣被的原料。'),(73,1,'桑蚕起源于哪里呢','桑蚕|起源于|','起源历史','text','桑蚕起源于中国，早在四、五千年前，我们祖先就栽桑养蚕，是举世公认的伟大发现之一。'),(74,1,'蚕宝宝吐丝前后有什么变化呢','蚕宝宝|吐丝|变化|','生长发育','text','蚕宝宝要吐丝的时候，颜色变的有点透明，这是因为它肚子里已经没有未消化的桑叶，它的肚子里全部都是透明的丝。一段时间后由于肚子里的丝都吐没了，蚕宝宝就会缩小很多，皱皱巴巴只有原来的三分之一大'),(75,1,'蚕宝宝在蚕茧中有什么变化呢','蚕宝宝|蚕茧|变化|','生长发育','text','蚕宝宝吐完丝后，就会蜕皮化蛹，渐渐的身体由白色变成深褐色，当再次蜕皮后就会化蛾破茧而出'),(76,1,'蚕卵是如果变成蚕宝宝的呢','蚕卵|变成|蚕宝宝|','生长发育','text','蚕卵外层是坚硬的卵壳，里面是卵黄与浆膜，受精卵中的胚胎在发育过程中不断摄取营养，逐渐发育成蚁蚕，它从卵壳中爬出来，卵壳空了之后变成白色或淡黄色。　　'),(77,1,'蚁蚕是什么样子的呢','蚁蚕|样子|','特征','text','蚕从蚕卵中孵化出来时，身体的颜色是褐色或赤褐色的，极细小，且多细毛，样子有点象蚂蚁，所以叫蚁蚕。蚁蚕长约2毫米，体宽约0.5毫米，它从卵壳中爬出来后，经过2~3小时就会进食桑叶。　　'),(78,1,'眠期的蚕宝宝是什么样子的呢','眠期|蚕宝宝|样子|','特征','text','当蚕宝宝食欲逐渐地有所减退乃至完全禁食，它吐出少量的丝，将腹足固定在蚕座上，头胸部昂起，不再运动，好像睡着了一样，称作“眠”。'),(79,1,'蚕蛾可以产多少卵呢','蚕蛾|可以|产|卵|','特征','text','雌蛾雄蛾交配后，雌蛾约花一个晚上可产下约500个卵,然后就会慢慢死去。'),(80,1,'蚕一共有几龄','蚕|龄|','生长发育','text','一共5龄。'),(81,1,'蚕的生长周期经历几个过程','蚕|生长|周期|经历|过程|','生长发育','text','幼虫、成虫、蛹、蛾'),(82,1,'蚕吃什么','蚕|吃|','生长发育','text','桑叶，现在我们研制了养蚕饲料'),(83,1,'蚕的一个周期大概几天','蚕|一个|周期|','生长发育','text','40天左右'),(84,1,'蚕蛾分雌雄吗','蚕蛾|雌雄|','生长发育','text','要分'),(85,1,'家蚕和野蚕长的一样吗','家蚕|野蚕|','特征','text','不一样'),(86,1,'蚕在哪个时期吐丝','蚕|时期|吐丝|','生长发育','text','上簇期'),(87,1,'蚕蛹是什么颜色','蚕蛹|颜色|','特征','text','褐色'),(88,1,'一个蚕可以产多少卵','一个|蚕|可以|产|卵|','孵化','text','200-300'),(89,1,'蚕有脚吗','蚕|脚|','生长发育','text','有'),(90,1,'蚕有眼睛吗','蚕|眼睛|','特征','text','有，幼虫时只有单眼，在头部两侧，各有6个小小的黑褐色突起；成虫时期（蛾期）变为复眼'),(91,1,'蚕宝宝怎么呼吸的','蚕宝宝|呼吸|','生理','text','蚕没有鼻子，蚕宝宝的呼吸器包括气门和气管两部分，身体侧面的两排小黑点是气门，是气管的开口，也是气体交换的门户，共有8对。气管是气门向内发出的分支，在一定程度上，它们就相当于蚕宝宝的鼻子啦！'),(92,1,'有哪些颜色的蚕茧','颜色|蚕茧|','蚕茧蚕丝','text','黄色，白色，粉色，橘色，绿色等等'),(93,1,'蚕茧的尺寸','蚕茧|尺寸|','蚕茧蚕丝','text','根据不同品种的蚕，它的茧型，茧色和大小会不尽相同。'),(94,1,'蚕适合生长的季节和月份','蚕|适合|生长|季节|月份|','生长发育','text','在南方，就温度、桑叶来源来说较适宜蚕生长繁殖，可养多代，大致可从4月养至9月'),(95,1,'小蚕和大蚕分别适合吃什么样的桑叶','小蚕|大蚕|适合|吃|桑叶|','生长发育','text','小蚕适合吃位于桑树枝杈顶端的嫩叶子，而大蚕适合吃枝条上位置靠下的老叶子'),(96,1,'适合蚕吃的桑叶品种','适合|蚕|吃|桑叶|品种|','食物','text','最适合吃的是叶桑，也吃果桑，但果桑重在产桑葚，对于蚕来说产的叶子没有专产叶子的叶桑好。'),(97,1,'桑树有哪些分类','桑树|分类|','食物','text','果桑和叶桑'),(98,1,'饲料怎样保存','饲料|保存|','饲料','text','放冰箱冷藏室（3-5℃）'),(99,1,'饲料上的水是什么','饲料|上|水是|','饲料','text','饲料里的水分和包装袋里空气在冰箱里冷凝成水，可用纸巾吸干，不影响饲料效果。'),(100,1,'饲料长霉了怎么办','饲料|霉|办|','饲料','text','开封就已经发霉的饲料，面上少量的霉可以削掉；如果很多霉，请扔掉这块饲料。'),(101,1,'饲料多少天更换','饲料|更换|','饲料','text','请按《操作手册》进度更换饲料。'),(102,1,'饲料能吃吗','饲料|能|吃|','饲料','text','蚕饲料只能给蚕宝吃，小朋友们可不能吃哦。'),(103,1,'一个蚕茧是多少根丝','一个|蚕茧|','蚕茧蚕丝','text','一个蚕茧就是一根蚕丝'),(104,1,'蚕茧的丝有多长','蚕茧|多|','蚕茧蚕丝','text','一个蚕茧的丝大概800-1200m长。'),(105,1,'饲料养蚕有什么禁忌','饲料养蚕有|禁忌|','饲料','text','最重要的，是不能移动眠蚕、不可日晒、蚕病处理不当等。请查看《操作手册》的“这样养蚕很危险”。'),(106,1,'蚕一定会吐丝吗','蚕|一定会|吐丝|','蚕茧蚕丝','text','发育不好的蚕宝，就吐不出什么丝了；另外，也有一种突变种叫“裸蛹”，天生不吐丝。'),(107,1,'为什么小蚕会吐丝','小蚕会|吐丝|','蚕茧蚕丝','text','当蚕宝饥饿或感觉自己往下掉时，它会吐丝哦。'),(108,1,'养小蚕必须盖盖子吗','养|小蚕|盖盖|子|','','text','是的，1-3龄的小蚕宝宝们吃食饲料很慢，一块饲料可以吃很多天，若敞着蚕盒，饲料一天就风干，所以必须盖盖子哦。'),(109,1,'养大蚕需要盖盖子吗','养|大蚕|需要|盖|盖子|','','text','蚕宝长大了，吃得多排泄多，若温度再高点儿，盖着养等于蚕盒秒变细菌培养箱，让排泄物充分发酵，蚕宝很容易生病。不同龄期不同对待。'),(110,1,'蚕沙多久清理一次','蚕沙|清理|一|','','text','3龄后，有时间可以每天清理；5龄后，必须每天清理了，干净的环境蚕宝才不容易生病。'),(111,1,'冰的饲料可以直接喂蚕吗','冰|饲料|可以|喂|蚕|','','text','不可以，请在常温放置两小时再打开喂蚕。'),(112,1,'小蚕可以用镊子移动吗','小蚕|可以|镊子|移动|','','text','蚁蚕建议不要动，哪怕是镊子，对它们来说都是洪荒之力'),(113,1,'DIY涂色是涂哪里','DIY|涂色|涂|','','text','盒子里配送的涂色卡上涂，蚕宝长到哪个时期就涂哪个阶段。'),(114,1,'养蚕盒要按斜线盖吗','养蚕|要|斜线|盖|','','text','是的，因为饲料需要保持水分，盖严了又影响空气流通。'),(115,1,'什么是蚕的龄','蚕|龄|','','text','蚕每长大蜕一次皮，龄期增加一龄，蚕一共有5龄。'),(116,1,'蚕的体温会变吗','蚕|体温|变|','','text','蚕宝是变温动物，缺乏让体温保持恒定的系统，体温与周围环境温度相近，因此对环境温度的变化十分敏感。'),(117,1,'饲料是什么成分','饲料|成分|','饲料','text','饲料里面包括蛋白质、淀粉、糖、矿物质、维生素等营养物质，不但味道好，而且营养丰富。'),(118,1,'怎样清理蚕沙','清理|蚕沙|','','text','清理小蚕蚕沙可以用吸尘器，也可以用勺子、刷子扫干净；5龄时，直接提开网格把蚕沙倒掉。'),(119,1,'小蚕用粉色还是蓝色盒养','小蚕|粉色|蓝色|养|','','text','都可以的，若开始用的粉色盒，后面就换蓝色盒；开始用的蓝色盒，后面就换粉色盒。'),(120,1,'蚕能看见东西吗','蚕|能|看见|东西|','特征','text','蚕的幼虫眼睛是单眼，只能感光，看不见东西，寻找食物全靠它强大的嗅觉。'),(121,1,'世界上最早养蚕的人是谁','世界上|养蚕|人|','','text','传说是远古时期嫘祖或马头娘最早开始养蚕，考古学家从河南贾湖遗址中发现了8500年前的蚕丝，说明最早的养蚕人是中国人，而且至少是在8500年前。'),(122,1,'最大的蚕有多大','蚕有多大|','生长发育','text','家蚕也就是桑蚕最大能长到六、七厘米。野蚕比如乌桕大蚕蛾的幼虫可以长到10到12厘米。'),(123,1,'蚕的寿命有多长','蚕|寿命|多|','生长发育','text','蚕一生要经历卵、幼虫、蛹和成虫四个阶段，约40天。'),(124,1,'蚕要吃多少桑叶','蚕|要|吃|桑叶|','生长发育','text',''),(125,1,'蚕蛾会飞吗','蚕蛾|飞|','生长发育','text','野蚕是可以飞的，从目前流传的部分古画中可以看出家蚕在古时候也是可以飞的，只是蚕驯养到今天越来越肥，已经飞不起来了。'),(126,1,'蚕蛹是死掉了吗','蚕蛹|死掉|','生长发育','text','蚕蛹不吃不动但是还是活着的，蚕蛹内部的器官在经历重塑改造的过程，从幼虫的组织器官变成成虫的组织器官。'),(127,1,'蚕会撒尿吗','蚕会|撒尿|','生长发育','text','蚕不会撒尿，原因是虽然蚕有类似于人的肾脏的器官马氏管，马氏管连接着肠道，但是马氏管没有在体外开口，所以不会排尿。'),(128,1,'蚕有鼻子吗','蚕|鼻子|','生长发育','text','蚕没有鼻子，但是能实现鼻子的功能。蚕的每节身体上都分布有气管起呼吸的作用。蚕有触角能够识别空气中的物质起闻的作用。'),(129,1,'怎么知道蚕高不高兴','知道|蚕|','','text','注意观察蚕的状态就知道啦，蚕要么是一直吃东西，要么是在睡觉。如果蚕生病了那就不高兴了。蚕生不同的病会表现出不同的特征。'),(130,1,'蚕会睡觉吗','蚕会|睡觉|','生长发育','text','蚕会睡觉，但是不像人是晚上睡觉，蚕睡觉的时期叫做眠期，蚕的幼虫一般要经历4个眠期。'),(131,1,'蚕会喝水吗','蚕会|喝水|','生长发育','text','蚕不直接喝水，但是它们的食物如桑叶中本身是含有水的，蚕靠吃桑叶就能够补充足够的水分了。'),(132,1,'蚕与蚕直接会说话吗','蚕|蚕|说话|','','text','蚕不会发声，它们靠触角和身体感受周围的环境和物体。'),(133,1,'哪里能找到蚕相关的书','能|找到|蚕|相关|书|','','text','可以在各地新华书店或者京东天猫等网上商城买到'),(134,1,'蚕的天敌是什么动物','蚕|天敌|动物|','生长发育','text','蚕的祖先野蚕在野外有很多天敌，比如鸟类和其他小型动物老鼠等，还有黄蜂、寄生蜂等昆虫。'),(135,1,'蚕丝有多长','蚕丝|多|','蚕茧蚕丝','text','蚕茧是由一根丝组成的，一般长1000多到2000米，实用种的蚕丝最长可以达到3000米以上'),(136,1,'蚕茧有多少根丝组成的','蚕茧|组成|','蚕茧蚕丝','text','蚕茧是由一根丝组成的'),(137,1,'蚕茧有哪些颜色','蚕茧|颜色|','蚕茧蚕丝','text','有粉红色、橘红色、黄色、黄绿色、白色等'),(138,1,'蚕茧为什么会有颜色','蚕茧|颜色|','蚕茧蚕丝','text','蚕茧的颜色与其食物有关，蚕可以把吃进去的桑叶中的类胡萝卜素和黄酮等色素转运到蚕丝中。'),(139,1,'蚕茧为什么会有不同的颜色','蚕茧|颜色|','蚕茧蚕丝','text','蚕茧的颜色与其食物有关，还与蚕吸收与转运色素的能力有关。野蚕是可以转运多种色素到蚕丝中去的，家养后有的转运色素的基因突变了，所以有的蚕就丢失了转运色素的能力。'),(140,1,'养蚕需要防止老鼠吗','养蚕|需要|防止|老鼠|','生长发育','text','需要。因为老鼠会把蚕吃掉，所以家里养蚕要防止老鼠侵害。'),(141,1,'养蚕需要避开苍蝇吗','养蚕|需要|避开|苍蝇|','生长发育','text','需要。苍蝇在蚕宝宝身上产卵后，会产生蝇蛆。'),(142,1,'蚕宝宝结茧时到处乱跑，为什么不会在提供的上簇工具上面结茧','蚕宝宝|结|茧|时|乱跑|提供|上簇|工具|结|茧|','生长发育','text','可能蚕宝宝没有找到合适的结茧场所，尽量让蚕宝宝放在提供的蚕格子中结茧。'),(143,1,'蚕宝宝吐丝有没有规律','蚕宝宝|吐丝|有没有|规律|','生长发育','text','蚕宝宝吐丝是很有规律的，头左右摇摆，吐丝曾“8”字形。'),(144,1,'蚕宝宝死了后身体会变大','蚕宝宝|死|后身|体会|变|','生长发育','text','不会变大，一般身体会收缩变小。'),(145,1,'为什么蚕宝宝吃相同的食物吐不同颜色的丝','蚕宝宝|吃|食物|吐|颜色|','蚕茧蚕丝','text','蚕茧的颜色与其食物有关，还与蚕吸收与转运色素的能力有关。野蚕是可以转运多种色素到蚕丝中去的，家养后有的转运色素的基因突变了，所以有的蚕就丢失了转运色素的能力。'),(146,1,'吃饲料与吃桑叶结茧颜色深浅不一样','吃|饲料|吃|桑叶|结|茧|颜色|深浅|','蚕茧蚕丝','text','食物来源不同，结茧颜色也会有差异。'),(147,1,'吃饲料比吃桑叶吐丝困难','吃|饲料|吃|桑叶|吐丝|','生长发育','text','没有差异。'),(148,1,'大蚕期，蚕粪更多，导致环境湿度更大，如何将蚕与蚕粪分开','大蚕期|蚕粪|导致|环境|湿度|蚕|蚕粪|分开|','','text','将大蚕放置于养蚕盒中的网格上进行饲养，蚕粪可以掉落到网格下面，进而实现蚕与蚕粪的分离。'),(149,1,'新加的新鲜饲料不能吸引蚕宝宝离开吃剩的饲料','加|饲料|不能|吸引|蚕宝宝|离开|吃|剩|饲料|','饲料','text','一般情况下蚕宝宝喜欢吃新鲜饲料，若不能吸引，可以适当人为操作。'),(150,1,'蚕宝宝身体变软发黑感染了什么病','蚕宝宝|身体|变软|发黑|感染|什么病|','蚕病','text','多数情况是由环境中的细菌感染引起，为细菌病。'),(151,1,'蚕宝宝感染细菌病有哪些','蚕宝宝|感染|细菌|病|','蚕病','text','主要有苏芸金杆菌、沙雷铁氏菌、气单孢杆菌等。'),(152,1,'蚕宝宝感染真菌病有哪些','蚕宝宝|感染|真菌|病|','蚕病','text','主要有链霉菌、黑曲霉、黄曲霉等。'),(153,1,'蚕宝宝感染病毒病有哪些','蚕宝宝|感染|','蚕病','text','主要有血液型脓病、中肠型脓病、病毒性软化病、浓核病等。'),(154,1,'蚕宝宝尾巴流血是怎么回事','蚕宝宝|尾巴|流血|回|事|','蚕病','text','很可能是蚕宝宝感染细菌病所致。'),(155,1,'饲料太湿润，影响蚕宝宝蜕皮','饲料|湿润|影响|蚕宝宝|蜕皮|','饲料','text','一般不会影响蚕宝宝蜕皮。'),(156,1,'饲养过程触碰蚕宝宝太多导致不健康','饲养|过程|触碰|蚕宝宝|导致|','生长发育','text','是的，可能会引入很多病菌，导致蚕宝宝生病。'),(157,1,'不能经常用手拿蚕宝宝玩','不能|手拿|蚕宝宝|玩|','养殖','text','是的，可能会引入很多病菌，导致蚕宝宝生病。'),(158,1,'如何实现一年四季都养蚕','实现|养蚕|','养殖','text','采用饲料全龄饲养家蚕，可以达到一年四季都养蚕的目的。'),(159,1,'蚕种如何提供以满足全年养蚕','蚕种|提供|满足|全年养|蚕|','养殖','text','蚕种可以低温保存，在需要用的时候取出解除滞育，蚕宝宝即可孵化。'),(160,1,'蚕宝宝有没有杂种优势','蚕宝宝|有没有|','生长发育','text','与其他生物一样，蚕宝宝也有杂种优势，杂交后的蚕宝宝在抗性、产丝能力等方面都有很好的表现。'),(161,1,'怎样除沙','除沙|','养殖','text','小蚕期除沙次数不宜过多，一般一龄眠除一次，如蚕沙不厚最好不除，仅轻轻扩座，撒上焦糠即可。二龄起、眠各除一次，三龄起、中、眠各除一次。主要用网除法，即喂蚕前先在蚕座上撒一层焦糠或石灰粉隔沙，然后将蚕网平铺在蚕匾上，接着给叶，使蚕儿爬上网吃桑，即可进行除沙。'),(162,1,'大蚕饲养的主要技术措施是什么','大蚕|饲养|主要|技术|措施|','养殖','text','⑴改善环境抓好通风防闷。⑵搭棚遮荫，防止热空气进入蚕室。⑶蚕座疏放、低放、勤喂薄饲。⑷抓好桑园管理，保证5龄期桑叶的数量和质量，使蚕儿吃饱、吃好，防止蚕儿食下老硬叶、过嫩叶、营养不良叶和水分不足叶。'),(163,1,'熟蚕何时上蔟','熟蚕|上蔟|','养殖','text','蚕儿5龄饷食后，经6～8天，食桑渐减，体色由青白色转为腊黄色，排软粪，随后停止食桑，排出大量绿色软粪，胸部透明，头抬高频频摆动寻找结茧位置，这时就要及时捉蚕或引蚕上蔟。'),(164,1,'上蔟方法有哪几种','上蔟|方法|','养殖','text','上蔟方法有两种：一是人工捉蚕上蔟（也叫人工拾取法），二是自动上蔟法。人工上蔟法是人工用手将熟蚕捉放到蚕蔟上。大蚕地面育可采用自动上法，即在盛熟期，将方格蔟平放在蚕座上，待熟蚕自动爬上来，如使用“登簇促进剂”，则上蔟效果更好。'),(165,1,'怎样做好蔟中管理','做|管理|','养殖','text','熟蚕背光性强，排泄粪尿量大，上蔟后吐丝结茧前，要保持蔟室光线稍暗均匀，避免熟蚕局部过密，上蔟后的第二天，当大多数熟蚕已经定位营茧，要将少数未找到位置而仍在蔟上爬游的蚕捉开另行上蔟，并打开门窗，通风排湿。蔟中保护的温度在25℃左右，干湿差3～4℃。如遇低温应适当加温排湿。'),(166,1,'何时采茧','何时采|茧|','养殖','text','熟蚕上蔟吐丝以后6天左右，当蚕已化蛹，体为棕黄色时是采茧的适期。'),(167,1,'怎样采茧','采|茧|','养殖','text','按上蔟顺序先上先采，采时先摘除死蚕烂茧，再采好茧、次茧、同宫、薄烂等分别存放出售，不得出售混合茧。'),(168,1,'采茧后怎样处理','采|茧|处理|','养殖','text','采下的鲜茧应尽快出售，防止蚕茧堆积发热。采茧最好用箩筐，以利通风换气，尽量避免用编织袋或布袋（尤其是化肥袋）装茧。'),(169,1,'桑蚕苗期如何防治病虫害','桑蚕|苗期|防治|病虫害|','蚕病','text','苗期病虫害较多，应加强防治。发现少数病株应及时拔除烧毁。喷药防治可用70%甲基托布津1000～1500倍或农用链霉素300～500PPM或粉锈宁1000倍等，防虫害可用80%敌敌畏或敌敌畏和乐果混合剂1000～1500倍液喷杀。'),(170,1,'识别雌雄蛹的特征','识别|雌雄|特征|','生长发育','text','最可靠的特征是腹部第八、九节：雌蚕第八、九节腹面有四个透明小点；雄蚕第九节腹面中央，有一个透明小点。不过一般而言，蚕在幼虫期的雌雄分辨的难度最大，除了高级技术员，一般蚕农往往很难分辨。'),(171,1,'识别蚕雌雄','识别|蚕|雌雄|','生长发育','text','最可靠的特征是腹部第八、九节：雌蚕第八、九节腹面有四个透明小点；雄蚕第九节腹面中央，有一个透明小点。不过一般而言，蚕在幼虫期的雌雄分辨的难度最大，除了高级技术员，一般蚕农往往很难分辨。'),(172,1,'雌雄蛾的分别','雌雄|蛾|','生长发育','text','除掉鉴别触角、腹部大小等特征外，最可靠的是鉴别雌雄蛾的外生殖器，例如雌蛾的交配孔附近有一片黑色骨板，雄蛾有成对的抱握器等，一般待雌雄蚕蛾孵化出自行交尾完毕分开后，鉴别最为简单。'),(173,1,'一龄蚕的图片','一|龄|蚕|图片|','养殖','image','ylc1.jpg,ylc2.jpg,ylc3.jpg'),(174,1,'刚孵出来的蚕的图片','孵|出来|蚕|图片|','养殖','image','ylc1.jpg,ylc2.jpg,ylc3.jpg'),(175,1,'一龄蚕长什么样子','一|龄|蚕|样子|','养殖','image','ylc1.jpg,ylc2.jpg,ylc3.jpg'),(176,1,'一龄蚕的照片','一|龄|蚕|照片|','养殖','image','ylc1.jpg,ylc2.jpg,ylc3.jpg'),(177,1,'蚕蛹什么样子','蚕蛹|样子|','养殖','image','cy1.jpg,cy2.jpg,cy3.jpg'),(178,1,'蚕蛹的图片','蚕蛹|图片|','养殖','image','cy1.jpg,cy2.jpg,cy3.jpg'),(179,1,'蚕蛹的照片','蚕蛹|照片|','养殖','image','cy1.jpg,cy2.jpg,cy3.jpg'),(180,1,'蚕蛾的图片','蚕蛾|图片|','养殖','image','ce1.jpg,ce2.jpg,ce3.jpg'),(181,1,'蚕蛾的长什么样子','蚕蛾|样子|','养殖','image','ce1.jpg,ce2.jpg,ce3.jpg');

/*Table structure for table `user_question` */

DROP TABLE IF EXISTS `user_question`;

CREATE TABLE `user_question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_problem` text NOT NULL,
  `system_answer` bigint(20) DEFAULT NULL,
  `ask_date` date NOT NULL,
  `flag` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user_question` */

insert  into `user_question`(`id`,`user_problem`,`system_answer`,`ask_date`,`flag`) values (1,'woshisanbing',2,'2021-03-12',1),(2,'我是伞兵',2,'2021-03-12',1),(3,'独立宣言',2,'2021-03-12',1),(4,'一眼万年',2,'2021-03-12',1),(5,'东百往事',2,'2021-03-12',1),(6,'蚕宝宝有多大',122,'2021-03-12',1),(7,'ghf',NULL,'2021-03-13',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
