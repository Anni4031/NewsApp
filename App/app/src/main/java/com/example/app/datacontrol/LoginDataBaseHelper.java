package com.example.app.datacontrol;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.example.app.MainYeActivity;
import com.example.app.model.News;
import com.example.app.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/12.
 */

public class LoginDataBaseHelper extends SQLiteOpenHelper {
    private Context mycontext;
    //数据库名
    private static final String DATABASENAME="logindatabase";
    //表名
    private static final String TABLE_USERS="users";
    private static final String TABLE_NEWS="news";
    //字段名
    private static final String USER_UID="uId";
    private static final String USER_UNAME="uName";
    private static final String USER_UPASSWORD="uPassword";
    private static final String USER_UCITY="uCity";
    private static final String USER_USEX="uSex";
    private static final String USER_UBIRTHDAY="uBirthday";
    private static final String NEWS_NID="nId";
    private static final String NEWS_NTILTE="nTitle";
    private static final String NEWS_NCONTENT="nContent";
    private static final String NEWS_NTYPE="nType";
    private static final String NEWS_NDATE="nDate";


    public LoginDataBaseHelper(Context context,int version) {
        super(context, DATABASENAME, null, version);
        mycontext=context;

    }

    //onCreate 只执行一次
    @Override
    public void onCreate(SQLiteDatabase db) {
        String userstr= "CREATE TABLE users( uId integer PRIMARY KEY AUTOINCREMENT, uName TEXT UNIQUE,uPassword TEXT,uCity TEXT,uSex TEXT,uBirthday TEXT)";
        db.execSQL(userstr);
        String newstr= "CREATE TABLE news( nId integer PRIMARY KEY AUTOINCREMENT, nTitle TEXT UNIQUE,nContent TEXT,nType TEXT,nDate TEXT)";
        db.execSQL(newstr);
        String addstr1=" INSERT INTO news (nTitle,nContent,nType,nDate) VALUES ('蒲公英可以长期泡水喝吗','蒲公英建议不要长期泡水喝。蒲公英虽然富含维生素A、维生素C、钾、铁、钙等营养元素，还具有清热解毒、消痛散结的作用，但是蒲公英性寒，长期服用会导致身体不适，所以不能长期泡水喝。','健康','2017-6-12 12:00')";
        db.execSQL(addstr1);
        String addstr2=" INSERT INTO news (nTitle,nContent,nType,nDate) VALUES ('闽北突降暴雨 浦城一小学受灾严重','中新网南平6月12日电 (张丽君 王树瑜 邵强)12日，记者在福建浦城官路乡中心小学看到，操场、一楼所有的教室地面上全是淤泥和灾后垃圾，老师和工人们正忙着清理。','社会','2017-6-12 13:00')";
        db.execSQL(addstr2);
        String addstr3=" INSERT INTO news (nTitle,nContent,nType,nDate) VALUES ('董卿被聘当大学教授，娱乐圈10大明星教授，你不一定都知道','恭喜主持人董卿。恭喜董卿成为华师大教授，以后可以称呼董教授了。董卿。华东师范大学教授。今天“人文精神能否照亮中国电视变革之路暨朗读者现象研讨会”在华东师范大学举行而当日董卿受聘华东师大兼职教授，参与并指导该校新闻传播类学科领域的学术研究和人才培养相关工作。','娱乐','2017-6-12')";
        db.execSQL(addstr3);
        String addstr4=" INSERT INTO news (nTitle,nContent,nType,nDate) VALUES ('曾经浑身是病、风一吹就感冒，92岁老人靠此法吃掉三高逆转健康','广西的黎秀金老人精神矍铄、面部光滑，脸上几乎看不到老人斑，头发也有转黑的迹象，走路带风都不用人扶的，说出她的真是年龄简直没人敢信，看上去60多岁的老人，今年竟然都92岁了！','健康','2017-6-12')";
        db.execSQL(addstr4);
        String addstr5=" INSERT INTO news (nTitle,nContent,nType,nDate) VALUES ('习近平会见卢森堡首相贝泰尔','习近平指出，中卢友好交往历史悠久。建交45年来，双方坚持真诚友好，坚持互利共赢。两国关系保持健康稳定发展。中方视卢森堡为欧盟内重要合作伙伴，愿同卢方一道努力，建设长期稳定、充满活力的伙伴关系，推动中卢" +
                "关系不断迈上新台阶。 习近平强调，双方要发扬优良传统，坚持平等相待、相互尊重，在涉及彼此核心利益和重大关切问题上相互理解、相互支持，把握两国关系正确发展方向。要提高两国务实合作质量，扩大优势，挖掘潜力，把金融等传统领域合作做大做强，加快培育航空运输、高新技术、绿色经济等新的合作增长点，实现更高水平的" +
                "互利共赢。要深化双方在“一带一路”建设框架内金融和产能等合作，中方支持建设郑州-卢森堡“空中丝绸之路”。要加强文化、教育、体育等人文交流，提高人员往来便利化水平。中方期待卢方在中欧关系中继续发挥积极作用，推动欧盟为中欧合作深入发展提供更多有利条件。 贝泰尔表示，卢中建交45年来，两国经贸、金融、交通、文化等领域交流合" +
                "作取得丰硕成果。我此次访华，旨在推进两国合作关系深入发展。卢中经济互补性强，卢方愿同中方在互尊互信基础上，互学互鉴，进一步拓展双方金融、投资、科技、旅游、人文等各领域交流合作、密切“一带一路”框架下合作。卢方主张欧中深化建设性对话交流合作，并愿为此发挥积极作用。','社会','2017-6-14')";
        db.execSQL(addstr5);
        String addstr6=" INSERT INTO news (nTitle,nContent,nType,nDate) VALUES ('李克强召开国务院常务会议:简化审批提质制造业 ','会议指出，制造业是实体经济的关键支撑。按照深化简政放权、放管结合、优化服务改革的要求，简化工业产品生产许可和审批程序，并强化部门监管责任和企业产品质 量安全主体责任，有利于放宽市场准入、激发社会投资活力、促进“中国制造”品质升级。经过多轮改革特别是2015年以来持续加大改革力度，工业产品生产许可已从最初的487类缩减到目前的60类，许可前置条件大幅取消。会议决定，按照今年政府工作报告部署，一是进一步压减生产许可。对能通过加强事中事后监管保障质量安全的输水管、蓄电池等19类产品取消事前生产许可；对产品质量较稳定，但与大众消费密切相关、直接涉及人体健康安全的电热毯、摩托车乘员头盔等产品，按照国际通行规则实行强制性认证，不再实施生产许可证管理。经上述调整后，实施生产许可证管理的产品将" +
                "减至38类。同时，对仍需实施生产许可，且量大面广、由地方管理更有效的化肥等8类产品，将许可权限下放给地方质检部门。二是授权质检总局在部分地区和行业试点简化生产许可证审批程序。取消发证前产品检验环节，改由企业提交有资质的检验检测机构出具的产品检验合格报告。将前置审查改" +
                "为后置，企业提交申请并作出保证产品质量安全的承诺后，可以先领取生产许可证再接受现场审查，实行“先证后核”。后续监管如发现不符合要求，即依法撤销许可证。三是加强事中事后监管。按照“双随机”方式加大抽查力度，增加抽查频次和品种，扩大覆盖面，尤其对此次取消许可管理的产品要实现抽" +
                "查全覆盖。会议认为，贯彻新发展理念，加快绿色金融体制机制创新，加大金融对改善生态环境、资源节约高效利用等的支持，对调结构、转方式，促进生态文明建设，具有重要意义，也是扎实履行中国政府对《巴黎协定》的承诺，应根据需要突出重点，有序探索推进。会议决定，在浙江、江西、广东、贵州、新疆" +
                "5省（区）选择部分地方，建设各有侧重、各具特色的绿色金融改革创新试验区，在体制机制上探索可复制可推广的经验。主要任务：一是支持金融机构设立绿色金融事业部或绿色支行，鼓励小额贷款、金融租赁公司等参与绿色金融业务。支持创投、私募基金等境内外资本参与绿色投资。二是鼓励发展绿色信贷，探索特许经" +
                "营权、项目收益权和排污权等环境权益抵质押融资。加快发展绿色保险，创新生态环境责任类保险产品。鼓励绿色企业通过发债、上市等融资，支持发行中小企业绿色集合债。加大绿色金融对中小城市和特色小城镇绿色建筑与基础设施建设的支持力度。三是探索建立排污权、水权、用能权等环境权益交易市场，建立企业污染排放、环境违法违规记录等信息" +
                "共享平台，建设绿色信用体系。推广和应用电子汇票、手机支付等绿色支付工具，推动绿色评级、指数等金融基础设施建设。四是强化财税、土地、人才等政策扶持，建立绿色产业、项目优先的政府服务通道。加大地方政府债券对公益性绿色项目的支持。通过放宽市场准入、公共服务定价等措施，完善收益和成本风险共担机制。五是建立绿色金融风险防范机制，" +
                "健全责任追究制度，依法建立绿色项目投融资风险补偿等机制。促进形成绿色金融健康发展模式。会议还研究了其他事项。','社会','2017-6-14')";
        db.execSQL(addstr6);
        Toast.makeText(mycontext,"成功创建数据库及创建数据表users和表news！",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {

            db.execSQL("DROP TABLE IF EXISTS users");
            db.execSQL("DROP TABLE IF EXISTS news");

            String userstr= "CREATE TABLE users( uId integer PRIMARY KEY AUTOINCREMENT, uName TEXT UNIQUE,uPassword TEXT,uCity TEXT,uSex TEXT,uBirthday TEXT)";
            db.execSQL(userstr);
            String newstr= "CREATE TABLE news( nId integer PRIMARY KEY AUTOINCREMENT, nTitle TEXT UNIQUE,nContent TEXT,nType TEXT,nDate TEXT)";
            db.execSQL(newstr);
            String addstr1=" INSERT INTO news (nTitle,nContent,nType,nDate) VALUES ('蒲公英可以长期泡水喝吗','蒲公英建议不要长期泡水喝。\n" +
                    "蒲公英虽然富含维生素A、维生素C、钾、铁、钙等营养元素，还具有清热解毒、消痛散结的作用，但是蒲公英性寒，长期服用会导致身体不适，所以不能长期泡水喝。','健康','2017-6-12 12:00')";
            db.execSQL(addstr1);
            String addstr2=" INSERT INTO news (nTitle,nContent,nType,nDate) VALUES ('闽北突降暴雨 浦城一小学受灾严重','中新网南平6月12日电 (张丽君 王树瑜 邵强)12日，记者在福建浦城官路乡中心小学看到，操场、一楼所有的教室地面上全是淤泥和灾后垃圾，老师和工人们正忙着清理。','社会','2017-6-12 13:00')";
            db.execSQL(addstr2);
            String addstr3=" INSERT INTO news (nTitle,nContent,nType,nDate) VALUES ('董卿被聘当大学教授，娱乐圈10大明星教授，你不一定都知道','恭喜主持人董卿。恭喜董卿成为华师大教授，以后可以称呼董教授了。董卿。华东师范大学教授。今天“人文精神能否照亮中国电视变革之路暨朗读者现象研讨会”在华东师范大学举行而当日董卿受聘华东师大兼职教授，参与并指导该校新闻传播类学科领域的学术研究和人才培养相关工作。','娱乐','2017-6-12')";
            db.execSQL(addstr3);
            String addstr4=" INSERT INTO news (nTitle,nContent,nType,nDate) VALUES ('曾经浑身是病、风一吹就感冒，92岁老人靠此法吃掉三高逆转健康','广西的黎秀金老人精神矍铄、面部光滑，脸上几乎看不到老人斑，头发也有转黑的迹象，走路带风都不用人扶的，说出她的真是年龄简直没人敢信，看上去60多岁的老人，今年竟然都92岁了！','健康','2017-6-12')";
            db.execSQL(addstr4);
            String addstr5=" INSERT INTO news (nTitle,nContent,nType,nDate) VALUES ('习近平会见卢森堡首相贝泰尔','习近平指出，中卢友好交往历史悠久。建交45年来，双方坚持真诚友好，坚持互利共赢。两国关系保持健康稳定发展。中方视卢森堡为欧盟内重要合作伙伴，愿同卢方一道努力，建设长期稳定、充满活力的伙伴关系，推动中卢" +
                    "关系不断迈上新台阶。 习近平强调，双方要发扬优良传统，坚持平等相待、相互尊重，在涉及彼此核心利益和重大关切问题上相互理解、相互支持，把握两国关系正确发展方向。要提高两国务实合作质量，扩大优势，挖掘潜力，把金融等传统领域合作做大做强，加快培育航空运输、高新技术、绿色经济等新的合作增长点，实现更高水平的" +
                    "互利共赢。要深化双方在“一带一路”建设框架内金融和产能等合作，中方支持建设郑州-卢森堡“空中丝绸之路”。要加强文化、教育、体育等人文交流，提高人员往来便利化水平。中方期待卢方在中欧关系中继续发挥积极作用，推动欧盟为中欧合作深入发展提供更多有利条件。 贝泰尔表示，卢中建交45年来，两国经贸、金融、交通、文化等领域交流合" +
                    "作取得丰硕成果。我此次访华，旨在推进两国合作关系深入发展。卢中经济互补性强，卢方愿同中方在互尊互信基础上，互学互鉴，进一步拓展双方金融、投资、科技、旅游、人文等各领域交流合作、密切“一带一路”框架下合作。卢方主张欧中深化建设性对话交流合作，并愿为此发挥积极作用。','社会','2017-6-14')";
            db.execSQL(addstr5);

            String addstr6=" INSERT INTO news (nTitle,nContent,nType,nDate) VALUES ('李克强召开国务院常务会议:简化审批提质制造业 ','会议指出，制造业是实体经济的关键支撑。按照深化简政放权、放管结合、优化服务改革的要求，简化工业产品生产许可和审批程序，并强化部门监管责任和企业产品质" +
                    "量安全主体责任，有利于放宽市场准入、激发社会投资活力、促进“中国制造”品质升级。经过多轮改革特别是2015年以来持续加大改革力度，工业产品生产许可已从最初的487类缩减到目前的60类，许可前置条件大幅取消。会议决定，按照今年政府工作报告部署，一是进一步压减生产许可。对能" +
                    "通过加强事中事后监管保障质量安全的输水管、蓄电池等19类产品取消事前生产许可；对产品质量较稳定，但与大众消费密切相关、直接涉及人体健康安全的电热毯、摩托车乘员头盔等产品，按照国际通行规则实行强制性认证，不再实施生产许可证管理。经上述调整后，实施生产许可证管理的产品将" +
                    "减至38类。同时，对仍需实施生产许可，且量大面广、由地方管理更有效的化肥等8类产品，将许可权限下放给地方质检部门。二是授权质检总局在部分地区和行业试点简化生产许可证审批程序。取消发证前产品检验环节，改由企业提交有资质的检验检测机构出具的产品检验合格报告。将前置审查改" +
                    "为后置，企业提交申请并作出保证产品质量安全的承诺后，可以先领取生产许可证再接受现场审查，实行“先证后核”。后续监管如发现不符合要求，即依法撤销许可证。三是加强事中事后监管。按照“双随机”方式加大抽查力度，增加抽查频次和品种，扩大覆盖面，尤其对此次取消许可管理的产品要实现抽查全覆盖。会议认为，贯彻新发展理念，加快绿色金融体制机制创新，加大金融对改善生态环境、资源节约高效利用等的支持，对调结构、转方式，促进生态文明建设，具有重要意义，也是扎实履行中国政府对《巴黎协定》的承诺，应根据需要突出重点，有序探索推进。会议决定，在浙江、江西、广东、贵州、新疆5省（区）选择部分地方，建设各有侧重、各具特色的绿色金融改革创新试验区，在体制机制上探索可复制可推广的经验。主要任务：一是支持金融机构设立绿色金融事业部或绿色支行，鼓励小额贷款、金融租赁公司等参与绿色金融业务。支持创投、私募基金等境内外资本参与绿色投资。二是鼓励发展绿色信贷，探索特许经营权、项目收益权和排污权等环境权益抵质押融资。加快发展绿色保险，创新生态环境责任类保险产品。鼓励绿色企业通过发债、上市等融资，支持发行中小企业绿色集合债。加大绿色金融对中小城市和特色小城镇绿色建筑与基础设施建设的支持力度。三是探索建立排污权、水权、用能权等环境权益交易市场，建立企业污染排放、环境违法违规记录等信息共享平台，建设绿色信用体系。推广和应用电子汇票、手机支付等绿色支付工具，推动绿色评级、指数等金融基础设施建设。四是强化财税、土地、人才等政策扶持，建立绿色产业、项目优先的政府服务通道。加大地方政府债券对公益性绿色项目的支持。通过放宽市场准入、公共服务定价等措施，完善收益和成本风险共担机制。五是建立绿色金融风险防范机制，健全责任追究制度，依法建立绿色项目投融资风险补偿等机制。促进形成绿色金融健康发展模式。会议还研究了其他事项。','社会','2017-6-14')";
            db.execSQL(addstr6);

            Toast.makeText(mycontext, "成功更新数据表users和表news！", Toast.LENGTH_LONG).show();
        }catch (Exception e)
        {
            Toast.makeText(mycontext, "系统异常，请联系管理员！", Toast.LENGTH_LONG).show();
            Log.e("LoginDataBaseHelper","onUpgrade error");
        }

    }
    //添加用户
    public void addData(User user)
    {
        //数据操作必须由SQLiteDatabase对象来实现
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(USER_UNAME,user.getuName());
        values.put(USER_UPASSWORD,user.getuPassword());
        values.put(USER_UCITY,user.getuCity());
        values.put(USER_USEX,user.getuSex());
        values.put(USER_UBIRTHDAY,user.getuBirthday());
        try {
            //nsert(String table, String nullColumnHack, ContentValues values)//table是指的表名，values需要添加记录的键值对//flag>0代表添加数据成功，否失败
            long flag = db.insert(TABLE_USERS, null, values);
            if (flag > 0)
                Toast.makeText(mycontext, user.getuName()+"注册成功！成功往users表中添加第" + flag + "条记录！", Toast.LENGTH_LONG).show();
            else if (flag == -1)
                Toast.makeText(mycontext, "数据添加失败！", Toast.LENGTH_LONG).show();
        }catch (Exception e)
        {
            Toast.makeText(mycontext, "系统异常，请联系管理员！", Toast.LENGTH_LONG).show();
        }
        finally {
            db.close();
        }
    }
    //查询用户,判断用户名和密码是否正确
    public User querydate(String uname,String upassword){
        User user=new User();
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=null;
        try
        {
            cursor= db.query("users",new String[]
                    {"uId","uPassword","uName","uCity","uSex","uBirthday"},"uName=? and "+"uPassword=?", new String[]{uname,upassword},null,null,null);

            if(cursor.moveToFirst()){
                String name1=cursor.getString(cursor.getColumnIndex("uName"));
                String password1=cursor.getString(cursor.getColumnIndex("uPassword"));
                Toast.makeText(mycontext, name1+"登录成功！", Toast.LENGTH_LONG).show();
                Intent intent=new Intent();
                //以对象方式传递数据
                Bundle bundle=new Bundle();
                bundle.putString("uname",name1);
                bundle.putString("password",password1);
                intent.putExtras(bundle);
                intent.setClass(mycontext,MainYeActivity.class);
                mycontext.startActivity(intent);
                return user;
            }
            new  AlertDialog.Builder(mycontext)
                    .setTitle("登录失败" )
                    .setMessage("用户名错误或密码错误!" )
                    .setPositiveButton("确定",null)
                    .setCancelable(false)
                    .show();
        }catch (Exception e)
        {
            Toast.makeText(mycontext, "系统异常，请联系管理员！", Toast.LENGTH_LONG).show();
            Log.d("SelectByuName",e.getMessage());
        }return null;}

    //查询所有新闻(后台)
    public List<News> queryall(){
        List<News> list=new ArrayList<News>();
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=null;
        try
        {
            cursor=db.rawQuery("select * from news",null);
            while (cursor.moveToNext()){
                News news=new News();
                news.setnId(cursor.getInt(cursor.getColumnIndex("nId")));
                news.setnTitle(cursor.getString(cursor.getColumnIndex("nTitle")));
                news.setnContent(cursor.getString(cursor.getColumnIndex("nContent")));
                news.setnType(cursor.getString(cursor.getColumnIndex("nType")));
                news.setnDate(cursor.getString(cursor.getColumnIndex("nDate")));
                list.add(news);
            }
            Toast.makeText(mycontext, "成功查询表news的所有数据！", Toast.LENGTH_LONG).show();
            return list;

        }catch (Exception e)
        {
            Toast.makeText(mycontext, "系统异常，请联系管理员！", Toast.LENGTH_LONG).show();
            Log.d("Select",e.getMessage());
        }return null;
    }

    //添加新闻
    public void addNewsData(News news)
    {
        //数据操作必须由SQLiteDatabase对象来实现
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(NEWS_NTILTE,news.getnTitle());
        values.put(NEWS_NCONTENT,news.getnContent());
        values.put(NEWS_NTYPE,news.getnType());
        values.put(NEWS_NDATE,news.getnDate());
        try {
            //nsert(String table, String nullColumnHack, ContentValues values)
            //table是指的表名，values需要添加记录的键值对
            //flag>0代表添加数据成功，否失败
            long flag = db.insert(TABLE_NEWS, null, values);
            if (flag > 0)
                Toast.makeText(mycontext, news.getnTitle()+"新闻添加成功！成功往news表中添加第" + flag + "条记录！", Toast.LENGTH_LONG).show();
            else if (flag == -1)
                Toast.makeText(mycontext, "数据添加失败！", Toast.LENGTH_LONG).show();
        }catch (Exception e)
        {
            Toast.makeText(mycontext, "系统异常，请联系管理员！", Toast.LENGTH_LONG).show();
        }
        finally {
            db.close();
        }
    }

    //根据修改前的标题来修改新闻
    public void updateDataBynId(News news,String oldnTitle)
    {

        //数据操作必须由SQLiteDatabase对象来实现
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(NEWS_NTILTE,news.getnTitle());
        values.put(NEWS_NCONTENT,news.getnContent());
        values.put(NEWS_NTYPE,news.getnType());
        values.put(NEWS_NDATE,news.getnDate());
        String[] whereArgs={oldnTitle};
        int flag;
        try{
            //  public int update (String table, ContentValues values, String whereClause, String[] whereArgs)
            //table 表名, values 键值对（更新数据的内容）,whereClause 更新条件, whereArgs更新条件的值
            flag=db.update(TABLE_NEWS,values,NEWS_NTILTE+"=?",whereArgs);
            if(flag>0)
                Toast.makeText(mycontext, "成功更新news表中" + flag + "条记录！", Toast.LENGTH_LONG).show();
            else if(flag==0)
                Toast.makeText(mycontext, "没有符合条件的记录！", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(mycontext, "数据更新失败！", Toast.LENGTH_LONG).show();
        }catch (Exception e)
        {
            Toast.makeText(mycontext, "系统异常，请联系管理员！", Toast.LENGTH_LONG).show();
        }
        finally {
            db.close();
        }
    }

    //根据新闻标题删除新闻
    public void deteleDataBynTitle(String bynTitle)
    {
        //数据操作必须由SQLiteDatabase对象来实现
        SQLiteDatabase db=getWritableDatabase();
        int flag;
        try {
            // public int delete (String table, String whereClause, String[] whereArgs)
            flag=db.delete(TABLE_NEWS,NEWS_NTILTE+" =?",new String[]{bynTitle});
            if(flag>0)
                Toast.makeText(mycontext, "成功删除news表中" + flag + "条记录！", Toast.LENGTH_LONG).show();
            else if(flag==0)
                Toast.makeText(mycontext, "没有符合条件的记录！", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(mycontext, "数据删除失败！", Toast.LENGTH_LONG).show();

        }catch (Exception e)
        {
            Toast.makeText(mycontext, "系统异常，请联系管理员！", Toast.LENGTH_LONG).show();
            Log.d("deteleDateBynTitle",e.getMessage());
        }
        finally {
            db.close();
        }
    }
    //降序排列所有新闻
    public List<News> queryallNews(){
        List<News> list=new ArrayList<News>();
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=null;
        try
        {
            cursor=db.rawQuery("select * from news order by nId DESC",null);
            while (cursor.moveToNext()){
                News news=new News();
                news.setnId(cursor.getInt(cursor.getColumnIndex("nId")));
                news.setnTitle(cursor.getString(cursor.getColumnIndex("nTitle")));
                news.setnContent(cursor.getString(cursor.getColumnIndex("nContent")));
                news.setnType(cursor.getString(cursor.getColumnIndex("nType")));
                news.setnDate(cursor.getString(cursor.getColumnIndex("nDate")));

                list.add(news);
            }
            Toast.makeText(mycontext, "成功查询表news的所有数据！", Toast.LENGTH_LONG).show();
            return list;
        }catch (Exception e)
        {
            Toast.makeText(mycontext, "系统异常，请联系管理员！", Toast.LENGTH_LONG).show();
            Log.d("Select",e.getMessage());
        }return null;
    }



    //根据用户名修改密码
    public void updatePwByuName(User user,String uName)
    {

        //数据操作必须由SQLiteDatabase对象来实现
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(USER_UPASSWORD,user.getuPassword());
        String[] whereArgs={uName};
        int flag;
        try{
            //  public int update (String table, ContentValues values, String whereClause, String[] whereArgs)
            //table 表名, values 键值对（更新数据的内容）,whereClause 更新条件, whereArgs更新条件的值
            flag=db.update(TABLE_USERS,values,USER_UNAME+"=?",whereArgs);
            if(flag>0)
                Toast.makeText(mycontext, "成功更新users表中" + flag + "条记录！", Toast.LENGTH_LONG).show();
            else if(flag==0)
                Toast.makeText(mycontext, "没有符合条件的记录！", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(mycontext, "数据更新失败！", Toast.LENGTH_LONG).show();
        }catch (Exception e)
        {
            Toast.makeText(mycontext, "系统异常，请联系管理员！", Toast.LENGTH_LONG).show();
        }
        finally {
            db.close();
        }
    }
    //查询所有用户
    public List<User> queryallUsers(){
        List<User> list=new ArrayList<User>();
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=null;
        try
        {
            cursor=db.rawQuery("select * from users",null);
            while (cursor.moveToNext()){
                User user=new User();
                user.setuId(cursor.getInt(cursor.getColumnIndex("uId")));
                user.setuName(cursor.getString(cursor.getColumnIndex("uName")));
                user.setuPassword(cursor.getString(cursor.getColumnIndex("uPassword")));
                user.setuSex(cursor.getString(cursor.getColumnIndex("uSex")));
                user.setuCity(cursor.getString(cursor.getColumnIndex("uCity")));
                user.setuBirthday(cursor.getString(cursor.getColumnIndex("uBirthday")));
                //
                list.add(user);
            }
            Toast.makeText(mycontext, "成功查询表users的所有数据！", Toast.LENGTH_LONG).show();
            return list;
        }catch (Exception e)
        {
            Toast.makeText(mycontext, "系统异常，请联系管理员！", Toast.LENGTH_LONG).show();
            Log.d("Select",e.getMessage());
        }return null;
    }
    //根据用户名删除用户
    public void deteleUserByuName(String byuName)
    {
        //数据操作必须由SQLiteDatabase对象来实现
        SQLiteDatabase db=getWritableDatabase();
        int flag;
        try {
            // public int delete (String table, String whereClause, String[] whereArgs)
            flag=db.delete(TABLE_USERS,USER_UNAME+" =?",new String[]{byuName});
            if(flag>0)
                Toast.makeText(mycontext, "成功删除users表中" + flag + "条记录！", Toast.LENGTH_LONG).show();
            else if(flag==0)
                Toast.makeText(mycontext, "没有符合条件的记录！", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(mycontext, "数据删除失败！", Toast.LENGTH_LONG).show();

        }catch (Exception e)
        {
            Toast.makeText(mycontext, "系统异常，请联系管理员！", Toast.LENGTH_LONG).show();
            Log.d("deteleDateBynTitle",e.getMessage());
        }
        finally {
            db.close();
        }
    }
    //根据用户名修改个人信息
    public void updateUserByuName(User user,String uName)
    {

        //数据操作必须由SQLiteDatabase对象来实现
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(USER_USEX,user.getuSex());
        values.put(USER_UCITY,user.getuCity());
        values.put(USER_UBIRTHDAY,user.getuBirthday());

        String[] whereArgs={uName};
        int flag;
        try{
            //  public int update (String table, ContentValues values, String whereClause, String[] whereArgs)
            //table 表名, values 键值对（更新数据的内容）,whereClause 更新条件, whereArgs更新条件的值
            flag=db.update(TABLE_USERS,values,USER_UNAME+"=?",whereArgs);
            if(flag>0)
                Toast.makeText(mycontext, "成功更新users表中" + flag + "条记录！", Toast.LENGTH_LONG).show();
            else if(flag==0)
                Toast.makeText(mycontext, "没有符合条件的记录！", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(mycontext, "数据更新失败！", Toast.LENGTH_LONG).show();
        }catch (Exception e)
        {
            Toast.makeText(mycontext, "系统异常，请联系管理员！", Toast.LENGTH_LONG).show();
        }
        finally {
            db.close();
        }
    }
    //根据用户名和密码查询个人信息
    public User queryByuNameAnduPassword(String uname,String upassword)
    {

        //数据操作必须由SQLiteDatabase对象来实现
        User user=new User();
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=null;
        try
        {
            // public Cursor query (String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit)
            //table-表名，columns-需要查询的字段，selection-查询条件，selectionArgs-查询条件的值，groupBy-分组条件，having-分组后的筛选条件，orderBy-排序条件，limit-分页
            cursor= db.query(TABLE_USERS,new String[]{USER_UID,USER_UPASSWORD,USER_UNAME,USER_UCITY,USER_USEX,USER_UBIRTHDAY},USER_UNAME+"=? and "+USER_UPASSWORD+"=?",
                    new String[]{uname,upassword},null,null,null);
            if(cursor.moveToFirst()){
                user.setuName(cursor.getString(cursor.getColumnIndex(USER_UNAME)));
                user.setuPassword(cursor.getString(cursor.getColumnIndex(USER_UPASSWORD)));
                user.setuCity(cursor.getString(cursor.getColumnIndex(USER_UCITY)));
                user.setuSex(cursor.getString(cursor.getColumnIndex(USER_USEX)));
                user.setuBirthday(cursor.getString(cursor.getColumnIndex(USER_UBIRTHDAY)));
                Toast.makeText(mycontext, "成功查询数据！", Toast.LENGTH_LONG).show();
                return user;
            }
            Toast.makeText(mycontext, "没有符合要求的数据！", Toast.LENGTH_LONG).show();

        }catch (Exception e)
        {
            Toast.makeText(mycontext, "系统异常，请联系管理员！", Toast.LENGTH_LONG).show();
            Log.d("SelectByuName",e.getMessage());
        }
        return null;
    }

}
