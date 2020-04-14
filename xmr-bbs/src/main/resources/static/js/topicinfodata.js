
$(function () {
    //总记录数
    var totalpageo;
    //当前页
    var currentpage;
    //到第几页,默认到第一页
    to_page(1);
});

//到问题页第几页
function to_page(pageno) {
    //加载完成之后,发送请求到服务器,拿到jason数据,构建列表数据
    var url = "/loadTopicInfo";
    $.ajax({
        type: "GET",
        url: url,
        data: {
            "pageNo": pageno,
            "pageSize": 15,
            "topicId": $(".topicdomain").attr("id"),
            "sortBy": $("#sortBy").attr("sortBy"),
            contentType: "application/json;charset=UTF-8"
        },
        beforeSend: function () {
            NProgress.start();
        },
        success: function (data) {
            NProgress.done();
            if (data.code == "1000") {
                var mypage=data.extend.page;
                if(mypage!=null){
                    $("#no_quetions").css({display:'none',});
                    //构建问题列表信息
                    build_question_list(data);
                    //构建分页信息
                    build_page_nav(data);
                }else{
                    $('.page_info-area').empty();
                    $(".pagination").empty();
                    $("#question_wrapper").empty();
                    $("#no_quetions").css({display:'block',});
                }
                //相关话题
                build_realted_topic(data);
                //关注该话题的用户
                build_topic_userlist(data);
                $("html,body").animate({scrollTop: 0}, 0);//回到顶端
            } else {
                layer.msg(data.extend.msg, {time: 2000, icon: 5, shift: 6}, function () {
                });
            }
        }
    });

}

function build_topic_userlist(data) {
    $("#userlist_wrapper").empty();
    var userList = data.extend.userList;
    if(userList!=null&&userList.length>0){
        $("#user_count").html(userList.length);
    }else {
        $("#user_count").html(0);
    }
    $.each(userList, function (index, item) {
        var img=$("<a href='/people/?id="+item.id+"'><img id='topic_user'  class=\"img-rounded\" src='"+item.avatarUrl+"'></a>");
        img.appendTo("#userlist_wrapper");
    })
}
function build_realted_topic(data) {
    $("#rtopic_wrapper").empty();
    var topics = data.extend.relatedTopics;
    $.each(topics, function (index, item) {
        var rtopic=$(" <span class=\"rtopic-tag\">\n" +
            "                                <a class=\"text\" href='/topic/"+item.id+"'>"+item.title+"</a>\n" +
            "                            </span>");
        rtopic.appendTo("#rtopic_wrapper");
    })
}


//构建分页导航
function build_page_nav(data) {
    var page = data.extend.page;
    //设置当前页
    currentpage = page.pageNum;
    //设置末页
    totalpageo = page.pages;
    $('.page_info-area').empty();
    $(".pagination").empty();
    $('.page_info-area').append("当前第" + page.pageNum + "页,共" + page.pages + "页,共" + page.total + "条记录")
    //分页导航
    var nav = $(".pagination");
    var firstLi = $("<li></li>").append($("<a>首页</a>").attr("href", "#"));
    var prli = $("<li></li>").append($("<a  aria-label='Previous'><span aria-hidden='true'>&laquo;</span></a>").attr("href", "#"));
    //首页
    firstLi.click(function () {
        to_page(1);
    });
    //上一页
    prli.click(function () {
        var target = page.pageNum - 1;
        target = target == 0 ? 1 : target;
        to_page(target);
    })
    var lastLi = $("<li></li>").append($("<a>末页</a>").attr("href", "#"));
    var nextli = $("<li></li>").append($("<a  aria-label='Next'><span aria-hidden='true'>&raquo;</span></a>").attr("href", "#"));
    //末页
    lastLi.click(function () {
        //alert("转到:"+page.pages)
        to_page(page.pages);
    })
    //下一页
    nextli.click(function () {
        var target = page.pageNum + 1;
        target = target < page.pages ? target : page.pages;
        to_page(target);
    })
    nav.append(prli);

    $.each(data.extend.page.navigatepageNums, function (index, item) {
        var li = $("<li></li>").append($("<a>" + item + "</a>").attr("href", "#"));
        if (data.extend.page.pageNum == item) {
            li.addClass("active");
        }
        //点击翻页
        li.click(function () {
            $(".pagination>li").removeClass("active");
            $(this).addClass("active");
            to_page(item);
            return false;
        })
        nav.append(li);
    })
    nav.append(nextli);

}

//构建questions列表
function build_question_list(data) {
    //清空
    $("#question_wrapper").empty();
    var questions = data.extend.page.list;
    $.each(questions, function (index, item) {
        var question = $("<div  class=\"question media\">\n" +
            "  <div class=\"  media-left \">\n" +
            "    <a href=\"/people?id=" + item.creator + "\">\n" +
            "      <img style='width: 45px;margin-right: 20px;' class='img-rounded' src=\" " + item.user.avatarUrl + " \" alt=\"...\">\n" +
            "    </a>\n" +
            "  </div>\n" +
            "  <div class=\"media-body\">\n" +
            "    <a  href='/question/" + item.id + "' class=\"media-heading question_title\">" + item.title + "</a>\n" +
            "  <br>  <span style=\"font-size: 12px;\">\n" +
            "                         <span class='question_type_tag'>" + item.typeName + "</span> • \n" +
            "                  "+item.user.name+"  •  <span style=\"font-size: 11px;\" class=\"iconfont icon-pinglun1\">" + item.commentCount + "</span>人评论 •\n" +
            "                     <span><small style='font-size: 11px;' class='iconfont icon-liulan1'></small>" + item.viewCount + "</span>次浏览 •\n" +
            "                        <span>" + item.likeCount + "</span>人点赞 •\n" +
            "                        发布于:<spanid=\"publish_time\"><span id='clock' class='iconfont icon-zuijingengxin' ></span>" + item.showTime + "</span>\n" +
            "            </span>\n" +
            "    <span style=\"float: right;color: #999999;font-size: 10px !important;\">\n" +
            "      <small class=\"\">" + item.typeName + "</small>\n\n" +
            "  </div>\n" +
            "</div>")
        question.appendTo("#question_wrapper");
    })

}
