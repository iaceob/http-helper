package name.iaceob.kit.httphelper.kit;


import name.iaceob.kit.httphelper.common.HttpConst;
import name.iaceob.kit.httphelper.entity.HttpEntity;
import org.testng.annotations.Test;

import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class HttpKitTest {

    @Test
    public void testPost() {
        String json = "{\"num\": 1, \"records\": [{\"id\":\"69794CED595C6757DF7E8CEBB1CD717C\",\"platform\":1,\"mid\":\"\",\"username\":\"\",\"nickname\":\"\",\"original_id\":\"\",\"original_uid\":\"\",\"original_name\":\"\",\"original_title\":\"\",\"original_url\":\"http://www.want-daily.com/portal.php?mod=view&aid=158808\",\"url\":\"http://www.want-daily.com/portal.php?mod=view&aid=158808\",\"home_url\":\"\",\"title\":\"聯想淨利腰斬 手機慘賠是禍首  旗下4種品牌須區隔市場 善用摩托羅拉猶可為\",\"type\":\"焦点新闻\",\"isharmful\":false,\"content\":\"大陸聯想集團日前公布最新財報，淨利潤較去年同期慘跌51%，外界將矛頭指向手機業務慘賠成了「啦啦隊」，更嚴重的，聯想欲重組手機業務，受限其手機品牌、型號太多，品牌精神薄弱，無法勾動消費者的使用意願，改革之路勢必舉步維艱，不是簡單的事。 聯想集團上周公布截至今年6月止的2015/2016財年第1季業績，總營業額為107億元（美元，下同），比去年同期成長3%，但淨利潤卻下跌51%至1.05億元，令人訝異；仔細觀察得知，手機事業群為「罪魁禍首」，因其虧損2.92億元，但還有更嚴重的內部問題要解決。 下季列6億美元重整 為了使業務回到成長軌道，聯想除在全球減少約3200名非生產製造員工，也計畫在下一季認列6億元組織重整費用、打消約3億元的手機庫存，這背後顯示，聯想手機滯銷問題嚴重，庫存壓力大。 業界人士分析，聯想去年用29億元收購摩托羅拉行動，本來想用這個金字招牌好好發揮，提升手機市占率排名，未料卻自己開發新品牌，品牌混亂，加上摩托羅拉手機出貨量不如預期，比去年同期縮減31%，聯想面臨嚴峻的挑戰。 據了解，聯想手機事業群有4支品牌，分別為聯想Lenovo，鎖定低階市場及電信營運商；第2個是神奇工場（ZUK），按網路思維打造；第3個是VIBE系列；最後則是主攻中高端市場的摩托羅拉。大陸手機市場擁擠，聯想新款手機ZUK Z1要在「紅海」中殺出血路，外界直言說「難如登天」。 聯想摩托整合要時間 「給聯想一點時間！」IT行業資深分析師孫永傑表示，聯想併購摩托羅拉，對其在印度等新興國家手機市場影響力提升，因為摩托羅拉品牌認同度高。聯想手機業務碰到的最大問題是旗下4個手機品牌該如何區隔，而聯想與摩托羅拉要完全整合，也需要一段過度時間。 「大量的廉價訂製機，透支了聯想手機的品牌！」市場人士認為，聯想手機業績低迷原因，最大的問題在於其手機產品線混亂，要走高階市場難度非常高，因先前推出大量低價手機所害，消費者會將聯想手機與便宜畫上等號。因此，聯想該好好發揮摩托羅拉的品牌價值，圍繞此品牌研發新產品，在市場或許還能占有一席之地。 ▲2014年11月7日，福州顧客在聯想賣場選購聯想產品。（中新社） ▲2006年，聯想手機秋季新品登場。（中新社）\",\"comment_count\":0,\"read_count\":0,\"favorite_count\":0,\"attitude_count\":0,\"repost_count\":0,\"video_url\":\"\",\"pic_url\":\" http://www.want-daily.com/data/attachment/portal/2015 8/20/wdp_1440022566_8951.jpg http://www.want-daily.com/data/attachment/portal/2015 8/20/wdp_1440022567_9119.jpg\",\"voice_url\":\"\",\"timestamp\":1440036449803,\"source_id\":635,\"lasttime\":1440036449803,\"server_id\":0,\"identify_id\":0,\"identify_md5\":\"xiayun\",\"first_time\":1440036449803,\"update_time\":0,\"geo\":\"\",\"receive_addr\":\"\",\"append_addr\":\"\",\"send_addr\":\"\",\"source_name\":\"旺报\",\"source_type\":2,\"country_code\":0,\"location_code\":0,\"province_code\":0,\"city_code\":0}]}";
        json = "{\"records\":[{\"id\":\"469A980623635E04787DEF04F43DB888\",\"platform\":6,\"url\":\"http://detail.zol.com.cn/cell_phone_index/subcate57_35358_list_1_s4328_9_1_115560_1.html\",\"title\":\"【合肥最新POMP1080p手机大全】合肥最新POMP1080p手机报价...\",\"type\":\"网页\",\"isharmful\":false,\"content\":\"不限三星vivo华为OPPO苹果TCL酷派大神中兴努比亚摩托罗拉金立华硕神舟魅族索尼移动飞利浦LG乐视联想HTC黑莓诺基亚微软奇酷一加小米朵唯ZUK8848中国移动格力夏普蓝魔SUGARinnos大可乐纽曼Acer宏碁天语海信康佳富可视VEBPPTV3DBOX谷歌先锋夏新长虹manta21克E人E本亚马逊爱我原点影驰斐讯果壳电子索野迪士尼长虹78点明基美猴王卡布奇诺优护柯达\\n三星vivo华为OPPO苹果TCL酷派大神中兴努比亚摩托罗拉金立华硕神舟魅族索尼移动飞利浦LG乐视联想HTC黑莓诺基亚微软奇酷一加小米朵唯ZUK8848中国移动格力夏普蓝魔SUGARinnos大可乐纽曼Acer宏碁天语海信康佳富可视VEBPPTV3DBOX谷歌先锋夏新长虹manta21克E人E本亚马逊爱我原点影驰斐讯果壳电子索野迪士尼长虹78点明基美猴王卡布奇诺优护柯达锤子科技美图小辣椒ivviGigasetIUNIsonim云狐YotaPhone百事nibiruAGM邦华大Q亿通Runbo奥克斯MANN100+青橙乐目黑米veaka小采波导米蓝新石器北斗青想图灵蜗牛华度泛泰富士通uphone卓普优米橙石福满多为美Fairphone天禄朗界德赛POMP雅马亚虎宝丽来欧恩\",\"timestamp\":1448294400000,\"source_id\":769,\"lasttime\":1448684282000,\"server_id\":3217,\"identify_md5\":\"iaceob\",\"keyword\":\"合肥 菜刀\",\"first_time\":1448336590000,\"ip\":\"220.181.76.30\",\"location\":\"北京市 电信互联网数据中心\",\"source_name\":\"有道搜索\",\"source_type\":1,\"country_code\":1,\"location_code\":110000,\"province_code\":100000,\"city_code\":100000}],\"num\":1}";
        // json = "{\"records\":[{\"identify_md5\":\"iaceob\",\"ip\":\"123.151.45.49\",\"city_code\":100000,\"source_type\":3,\"type\":\"腾讯微博\",\"server_id\":202,\"province_code\":10000,\"platform\":3,\"url\":\"http:\\/\\/t.qq.com\\/p\\/t\\/422849122464039\",\"content\":\"少骗我\",\"country_code\":1,\"lasttime\":1444872650708,\"location_code\":100000,\"first_time\":1444872650708,\"nickname\":\"唯美-我爱 \",\"source_id\":8,\"id\":\"15B023B51A94BF1CA89ADC981E5202A1\",\"pic_url\":\"http:\\/\\/t2.qpic.cn\\/mblogpic\\/0391f27c9f401528ba02\\/120,http:\\/\\/mat1.gtimg.com\\/www\\/mb\\/images\\/face\\/33.gif\",\"source_name\":\"腾讯微博\",\"timestamp\":1444834938000,\"username\":\"shouji1345268085\",\"location\":\"\"}], \"num\":1}";
        String url = "http://192.168.32.17:20000/sentiment/index";
        url = "http://121.31.12.34:28093/sentiment/index";
        url = "http://192.168.32.17:20000/sentiment/index";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(HttpConst.CONTENT_TYPE, "application/json");
        HttpEntity he = HttpKit.post(url, null, json, Charset.forName("UTF-8"));
        System.out.println(he.getHtml());
    }


    @Test
    public void ttd1() {
        System.out.println(Timestamp.valueOf("2015-11-24 11:43:10").getTime());
    }


}