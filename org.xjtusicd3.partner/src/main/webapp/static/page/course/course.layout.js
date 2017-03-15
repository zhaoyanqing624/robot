define(function(require) {
	require("common"), require("/org.xjtusicd3.partner/static/component/logic/common/userinfo.js"), $
			.ajax({
				type : "GET",
				url : "/course/AjaxCourseMembers",
				data : {
					ids : GC.course.id
				},
				dataType : "json",
				success : function(a) {
					if (0 == a.result) {
						var c = a.data[0];
						$(".js-learn-num").text(c.numbers)
					} else
						console.log("获取学习人数错误")
				}
			}), $.ajax({
		type : "GET",
		url : "/course/ajaxcourselearn",
		data : {
			cid : GC.course.id
		},
		dataType : "json",
		success : function(a) {
			0 == a.result ? 1 == a.data ? $(".js-follow-action").addClass(
					"icon-star2").attr({
				"data-cid" : GC.course.id,
				"data-cmd" : "cancle",
				title : "取消收藏"
			}) : $(".js-follow-action").addClass("icon-star_outline").attr({
				"data-cid" : GC.course.id,
				"data-cmd" : "follow",
				title : "收藏"
			}) : -11 == a.result ? $(".js-follow-action").addClass(
					"icon-star_outline").attr({
				"data-cid" : GC.course.id,
				"data-cmd" : "follow",
				title : "收藏"
			}) : console.log("错误")
		}
	});
	var a = {
		getData : function(a, c, j, C) {
			var g = this;
			$(a).each(function(h) {
				$.ajax({
					type : "GET",
					url : "/course/" + a[h],
					data : {
						cid : GC.course.id
					},
					dataType : "json",
					success : function(a) {
						if (0 == a.result) {
							var $ = a.data;
							$.length && g.bindData($, c[h], j[h], C)
						}
					}
				})
			})
		},
		bindData : function(a, c, j, C) {
			var g, h = "", G = "";
					$(a)
							.each(
									function(c) {
												"courserecom" == j
														&& (g = 2 == a[c].type ? "//coding.imooc.com/class/"
																+ a[c].id
																+ ".html"
																: "/learn/"
																		+ a[c].id),
												"planrecom" == j
														&& (g = "/course/programdetail/pid/"
																+ a[c].id),
												G += '<li>                            <a href="'
														+ g
														+ '" target="_blank">                                <span class="name autowrap">'
														+ a[c].name
														+ "</span>                            </a>                        </li>"
									}),
					h = '<div class="box  mb40">                        <h4>'
							+ c
							+ '</h4>                        <ul class="js-'
							+ j
							+ ' other-list">                            '
							+ G
							+ "                        </ul>                    </div>",
					"courserecom" == j ? $(C).append(h) : $(C).prepend(h)
		}
	};
	a.getData([ "ajaxplanrecom", "ajaxcourserecom" ], [ "相关学习路径", "推荐课程" ], [
			"planrecom", "courserecom" ], ".js-recom-box")
});