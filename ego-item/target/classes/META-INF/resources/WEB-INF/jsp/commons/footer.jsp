<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="w" clstag="homepage|keycount|home2013|37a">
	<div id="service-2013">
		<dl class="fore1">
			<dt><b></b><strong>购物指南</strong></dt>
			<dd>
				<div><a href=""  rel="nofollow">购物流程</a></div>
				<div><a href=""  rel="nofollow">会员介绍</a></div>
				<div><a href=""  rel="nofollow">团购/机票</a></div>
				<div><a href=""  rel="nofollow">常见问题</a></div>
				<div><a href=""  rel="nofollow">大家电</a></div>
				<div><a href=""  rel="nofollow">联系客服</a></div>
			</dd>
		</dl>
		<dl class="fore2">		
			<dt><b></b><strong>配送方式</strong></dt>
			<dd>
				<div><a href=""  rel="nofollow">上门自提</a></div>
				<div><a href=""  rel="nofollow">211限时达</a></div>
				<div><a href=""  rel="nofollow">配送服务查询</a></div>
				<div><a href=""  rel="nofollow">配送费收取标准</a></div>
				<div><a href=""  rel="nofollow">如何送礼</a></div>
				<div><a href="" >海外配送</a></div>
			</dd>
		</dl>
		<dl class="fore3">
			<dt><b></b><strong>支付方式</strong></dt>
			<dd>
				<div><a href=""  rel="nofollow">货到付款</a></div>
				<div><a href=""  rel="nofollow">在线支付</a></div>
				<div><a href=""  rel="nofollow">分期付款</a></div>
				<div><a href=""  rel="nofollow">邮局汇款</a></div>
				<div><a href=""  rel="nofollow">公司转账</a></div>
			</dd>
		</dl>
		<dl class="fore4">		
			<dt><b></b><strong>售后服务</strong></dt>
			<dd>
				<div><a href="" target="" rel="nofollow">售后政策</a></div>
				<div><a href="" target="" rel="nofollow">价格保护</a></div>
				<div><a href="" target="" rel="nofollow">退款说明</a></div>
				<div><a href="" target="" rel="nofollow">返修/退换货</a></div>
				<div><a href="" target="" rel="nofollow">取消订单</a></div>
			</dd>
		</dl>
		<dl class="fore5">
			<dt><b></b><strong>特色服务</strong></dt>
			<dd>		
				<div><a href="" target="">夺宝岛</a></div>
				<div><a href="" target="">DIY装机</a></div>
				<div><a href="" target="" rel="nofollow">延保服务</a></div>
				<div><a href="" target="" rel="nofollow">易购E卡</a></div>
				<div><a href="" target="" rel="nofollow">节能补贴</a></div>
				<div><a href="" target="" rel="nofollow">易购通信</a></div>
			</dd>
		</dl>
		<div class="fr">
			<div class="sm" id="branch-office">
				<div class="smt">
					<h3>易购自营覆盖区县</h3>
				</div>
				<div class="smc">
					<p>易购已向全国1859个区县提供自营配送服务，支持货到付款、POS机刷卡和售后上门服务。</p>
					<p class="ar"><a href="" target="">查看详情&nbsp;></a></p>
				</div>
			</div>
		</div>
		<span class="clr"></span>
	</div>
</div>
<div class="w" clstag="homepage|keycount|home2013|38a">
	<jsp:include page="footer-links.jsp"></jsp:include>
</div>
<script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
<script type="text/javascript" src="/js/jquery-extend.js"></script>
<script type="text/javascript" src="/js/lib-v1.js" charset="utf-8"></script>
<script type="text/javascript" src="/js/ego.js" charset="utf-8"></script>
<script type="text/javascript"> (function(){
var A="<strong>热门搜索：</strong><a href='' target='' style='color:#ff0000' clstag='homepage|keycount|home2013|03b1'>校园之星</a><a href='' target=''>办公打印</a><a href='' target=''>美菱冰箱</a><a href='' target=''>无肉不欢</a><a href='' target=''>万件好货</a><a href='' target=''>iPhone6</a><a href='' target=''>哈利波特</a><a href='' target=''>美模接驾</a>";
var B=["java","apple","LG G3","天梭","保温杯","三个爸爸"];
B=pageConfig.FN_GetRandomData(B);
$("#hotwords").html(A);
var _searchValue = "${query}";
if(_searchValue.length == 0){
	_searchValue = B;
}
$("#key").val(_searchValue).bind("focus",function(){if (this.value==B){this.value="";this.style.color="#333"}}).bind("blur",function(){if (!this.value){this.value=B;this.style.color="#999"}});
})();
</script>