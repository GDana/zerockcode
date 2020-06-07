<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board Read</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board Read Page</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<div class="form-group">
					<label>Bno</label><input class="form-control" name="bno"
						value='<c:out value="${board.bno}" />' readonly="readonly">
				</div>
				<div class="form-group">
					<label>Title</label><input class="form-control" name="title"
						value='<c:out value="${board.title}" />' readonly="readonly">
				</div>
				<div class="form-group">
					<label>Text area</label>
					<textarea class="form-control" rows="3" name="content"
						readonly="readonly"><c:out value="${board.content}" /></textarea>
				</div>
				<div class="form-group">
					<label>Writer</label><input class="form-control" name="writer"
						value='<c:out value="${board.writer}" />' readonly="readonly">
				</div>
				<button data-oper="modify" class="btn btn-default">Modify</button>
				<button data-oper="list" class="btn btn-info">List</button>

				<form id="operForm" action="/board/modify" method="get">
					<input type="hidden" id="bno" name="bno"
						value="<c:out value="${board.bno}"/>"> <input
						type="hidden" id="bno" name="pageNum"
						value="<c:out value="${cri.pageNum}"/>"> <input
						type="hidden" id="bno" name="amount"
						value="<c:out value="${cri.amount}"/>"> <input
						type="hidden" id="bno" name="keyword"
						value="<c:out value="${cri.keyword}"/>"> <input
						type="hidden" id="bno" name="type"
						value="<c:out value="${cri.type}"/>">
				</form>
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<i class="fa fa-comments fa-fw">Reply</i>
				<button id="addReplyBtn" class="btn btn-primary btn-xs pull-right">New
					Reply</button>
			</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<ul class="chat">
					<li class="left clearfix" data-rno="22">
						<div class="header">
							<strong class="primary-font">user00</strong> <small
								class="pull-right text-muted">2020-04-15 17:48</small>
						</div>
						<p>Good job!</p>
					</li>
				</ul>
			</div>
			<!-- /.panel-body -->
			<div class="panel-footer"></div>
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label>Reply</label> <input class="form-control" name='reply'
						value='New Reply!!!!'>
				</div>
				<div class="form-group">
					<label>Replyer</label> <input class="form-control" name='replyer'
						value='replyer'>
				</div>
				<div class="form-group">
					<label>Reply Date</label> <input class="form-control"
						name='replyDate' value='2018-01-01 13:13'>
				</div>
			</div>
			<div class="modal-footer">
				<button id='modalModBtn' type="button" class="btn btn-warning">Modify</button>
				<button id='modalRemoveBtn' type="button" class="btn btn-danger">Remove</button>
				<button id='modalRegisterBtn' type="button" class="btn btn-primary">Register</button>
				<button id='modalCloseBtn' type="button" class="btn btn-default">Close</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->



<script type="text/javascript" src="/resources/js/reply.js"></script>

<script type="text/javascript">
// 아래 Ajax 통신을 위한 기능 테스트했던 코드들을 토대로 실제로 구동할 코드 작성

$(document).ready(function(){
	
	var bnoValue = '<c:out value="${board.bno}" />';
	var replyUL = $(".chat");
	
	showList(1);	//page 번호가 없는 경우 자동으로 1페이지가 되도록 설정
	
	function showList(page){	//page 번호를 파라미터로 받는다
		
		//댓글 페이징 처리 후
		replyService.getList({bno : bnoValue, page : page || 1}, function(replyCnt, list){
			//console.log("replyCnt : " + replyCnt);
			//console.log("list : " + list);
			console.log(page);
			console.log(replyCnt);
			console.log(list);
			
			if(page == -1){	//page번호가 -1로 전달되면 마지막 페이지를 찾아서 다시 호출
				pageNum = Math.ceil(replyCnt/10.0);
				console.log("pageNum = " + pageNum);
				showList(pageNum);
				return;
			}
			
			var str = "";
			if(list == null || list.length == 0){
				return;
			}
			for(var i = 0, len = list.length || 0; i < len; i++){
			       str +="<li class='left clearfix' data-rno='"+list[i].rno+"'>";
			       str +="	<div><div class='header'><strong class='primary-font'>"+list[i].replyer+"</strong>"; 
			       str +="		<small class='pull-right text-muted'>"+replyService.displayTime(list[i].replyDate)+"</small></div>";
			       str +="			<p>"+list[i].reply+"</p></div></li>";
			}
			replyUL.html(str);
			showReplyPage(replyCnt);
		});	//end function
		
		/* 댓글 페이징 처리 전
			bno : bnoValue 의 댓글을 조회
		replyService.getList({bno : bnoValue, page : 1}, function(list){			
			var str = "";
			if(list == null || list.length == 0){
				replyUL.html("");
				return;
			}
			for(var i = 0, len = list.length || 0; i < len; i++){
			       str +="<li class='left clearfix' data-rno='"+list[i].rno+"'>";
			       str +="	<div><div class='header'><strong class='primary-font'>"+list[i].replyer+"</strong>"; 
			       str +="		<small class='pull-right text-muted'>"+replyService.displayTime(list[i].replyDate)+"</small></div>";
			       str +="			<p>"+list[i].reply+"</p></div></li>";
			}
			replyUL.html(str);
		});	//end function
		*/
	}// end showList
	
	var pageNum = 1;
    var replyPageFooter = $(".panel-footer");
    
    function showReplyPage(replyCnt){
      var endNum = Math.ceil(pageNum / 10.0) * 10;  
      var startNum = endNum - 9; 
      
      var prev = startNum != 1;
      var next = false;
      
      if(endNum * 10 >= replyCnt){
        endNum = Math.ceil(replyCnt/10.0);
      }
      
      if(endNum * 10 < replyCnt){
        next = true;
      }
      
      var str = "<ul class='pagination pull-right'>";
      
      if(prev){
        str+= "<li class='page-item'><a class='page-link' href='"+(startNum -1)+"'>Previous</a></li>";
      }
      
      for(var i = startNum ; i <= endNum; i++){
        var active = pageNum == i? "active":"";
        
        str+= "<li class='page-item "+active+" '><a class='page-link' href='"+i+"'>"+i+"</a></li>";
      }
      
      if(next){
        str+= "<li class='page-item'><a class='page-link' href='"+(endNum + 1)+"'>Next</a></li>";
      }
      
      str += "</ul></div>";
      
      //console.log(str);
      replyPageFooter.html(str);
    }
	
	var modal = $(".modal");
	var modalInputReply = modal.find("input[name='reply']");
	var modalInputReplyer = modal.find("input[name='replyer']");
	var modalInputReplyDate = modal.find("input[name='replyDate']");
	
	var modalModBtn = $("#modalModBtn");
	var modalRemoveBtn = $("#modalRemoveBtn");
	var modalRegisterBtn = $("#modalRegisterBtn");
	
	$("#addReplyBtn").on("click", function(e){
		modal.find("input").val("");
		modalInputReplyDate.closest("div").hide();
		modal.find("button[id != 'modalCloseBtn']").hide();
		
		modalRegisterBtn.show();
		
		$(".modal").modal("show");
	});
	
	modalRegisterBtn.on("click", function(e){
		var reply = {
				reply : modalInputReply.val(),
				replyer : modalInputReplyer.val(),
				bno : bnoValue
		};
		replyService.add(reply, function(result){
			//alert(result);
			alert("댓글 등록 완료");
			
			modal.find("input").val("");
			modal.modal("hide");
			
			// showList(1);	//댓글 페이징 처리 전
			showList(-1);		// 댓글 페이징 처리 후, 사용자가 새로운 댓글을 추가하면 showList(-1)을 호출하여 우선 전체 댓글의 숫자를 파악한다
		});
	});
	
	/*
	1. ul의 클래스인 '.chat'에 이벤트를 걸고
	2. 실제 이벤트의 대상은 <li>태그가 된다	
	*/
	$(".chat").on("click", "li", function(e){	 
		var rno = $(this).data("rno");
		//console.log(rno);
		
		replyService.get(rno, function(reply){
			modalInputReply.val(reply.reply);
			modalInputReplyer.val(reply.replyer);
			modalInputReplyDate.val(replyService.displayTime(reply.replyDate)).attr("readonly", "readonly");
			modal.data("rno", reply.rno);
			
			modal.find("button[id != 'modalCloseBtn']").hide();
			modalModBtn.show();
			modalRemoveBtn.show();
			
			$(".modal").modal("show");
		})
	})
	
	modalModBtn.on("click", function(e){
		var reply = {
				rno : modal.data("rno"),
				reply : modalInputReply.val()
		};
		replyService.update(reply, function(result){
			//alert(result);	//result값으로 'seccess'가 뜸
			alert("댓글 수정 완료");
			modal.modal("hide");			
			//showList(1);	//댓글 페이징 처리전
			showList(pageNum);	//댓글 페이징 처리후
		});
	});
	
	modalRemoveBtn.on("click", function(e){
		var reply = modal.data("rno");
		
		replyService.remove(reply, function(result){
			alert("댓글 삭제 완료");
			modal.modal("hide");			
			//showList(1);	//댓글 페이징 처리전
			showList(pageNum);	//댓글 페이징 처리후
		});
	});
	
	replyPageFooter.on("click", "li a", function(e){
		e.preventDefault();
		console.log("page click");
		
		var targetPageNum = $(this).attr("href");
		console.log("targetPageNum : " + targetPageNum);
		
		pageNum = targetPageNum;
		showList(pageNum);
	})
});
</script>

<script type="text/javascript">

$(document).ready(function(){
	var operForm = $("#operForm");
	
	$("button[data-oper='modify']").on("click", function(e){
		operForm.attr("action", "/board/modify").submit();
	});
	
	$("button[data-oper='list']").on("click", function(e){
		operForm.find("#bno").remove();
		operForm.attr("action", "/board/list")
		operForm.submit();
	});
});
</script>

<script>
	/*
		아래는 Ajax 통신을 위한 기능 테스트 코드들! 
		이 코드들을 바탕으로 기능 구현
		jquery를 이용하지 않는 코드	
	*/
	
	/* 
	var bnoValue = '<c:out value="${board.bno}" />';
	
	//bno : bnoValue 에 댓글 추가
	replyService.add(
		{reply : "JS Test", replyer: "tester", bno: bnoValue},
		function(result){
			alert("result: " + result)
		}
	);
	
	//bno : bnoValue 의 댓글을 조회
	replyService.getList({bno : bnoValue, page : 1}, function(list){
		for(var i = 0, len = list.length || 0; i < len; i++){
			console.log(list[i]);
		}
	});
	*/
	
	/*
	//bno = 217의 rno=25 댓글 삭제 테스트
	replyService.remove(25, function(count){
		console.log(count);
		
		if(count === "success"){
			alert("[삭제] 성공");
		}
	}, function(err){
		alert("[삭제] 에러");
	});
	
	replyService.update({
		rno : 24,
		bno : bnoValue,
		reply : "replyService.add 로 추가한 댓글 수정테스트"
	}, function(result){
		alert("[수정] 성공");
	});
	*/
	
	/*
	replyService.get(24, function(data){
		console.log(data);
	})
	*/
</script>


<%@include file="../includes/footer.jsp"%>