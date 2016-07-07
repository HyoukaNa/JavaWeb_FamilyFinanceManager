/**
 * Created by yang on 16-6-30.
 */
$(document).ready(function () {
    var temp=$(this).parent().siblings();
    $("a.delete").click(function () {
        if(confirm("是否确认删除？")){
            $.get("",{
                service:"delete",
                id:temp.eq(1).text(),
            },function (data,status) {
                if(data=="true"){
                    alert("已成功删除");
                    $(this).parent().parent().remove();
                }else{
                    alert("删除失败");
                }
            });
        }else{
            return false;
        }
    })
});
