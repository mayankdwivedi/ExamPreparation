<script type="text/javascript"
    src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
    function crunchifyAjax() {
        $.ajax({
            url : 'ajaxtest.html',
            success : function(data) {
                $('#timer').html(data);
            }
        });
    }
</script>

<script type="text/javascript">
    var intervalId = 0;
    intervalId = setInterval(crunchifyAjax, 60000);
</script>
<div class="firstmain">
<div class="span10"></div>
<div class="span2" id="timer">
</div>
</div>