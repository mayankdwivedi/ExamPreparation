<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row-fluid">
<div class="span2">
</div>

<div class="span8">
<div class="row-fluid">

<div class="span3">
<a href="aptitude.html" class="span examSectionsActive">Aptitude</a>
</div>

<div class="span3">
<a href="reasoning.html" class="span examSections">Reasoning</a>
</div>

<div class="span3">
<a href="english.html" class="span examSections">English</a>
</div>

<div class="span3">
<a href="gk.html" class="span examSections">GK</a>
</div>

</div>
</div>

<div class="span2">
</div>
</div>

<br>
<br>


<div class="row-fluid">
<div class="span2">
</div>

<div class="span5">

${questionMap.que} <br>
<input type="radio" name="q1" style="width:16px; height:16px;"/>&nbsp;&nbsp;${questionMap.a}<br>
<input type="radio" name="q1" style="width:16px; height:16px;"/>&nbsp;&nbsp;${questionMap.b}<br>
<input type="radio" name="q1" style="width:16px; height:16px;"/>&nbsp;&nbsp;${questionMap.c}<br>
<input type="radio" name="q1" style="width:16px; height:16px;"/>&nbsp;&nbsp;${questionMap.d}

<br>
<br>
<br>

<button class="btn">Previous</button>
<button class="btn">Review Next </button>
<button class="btn">Next</button>

</div>

<div class="span3">
<c:forEach var="questionNumberListItem" items="${questionNumberList}">
<a href="aquestions.html?originalQno=${questionNumberListItem.value}" class="btn">${questionNumberListItem.key}</a>
</c:forEach>



</div>

<div class="span2">

</div>
</div>