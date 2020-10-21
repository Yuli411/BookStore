<%--
  Created by IntelliJ IDEA.
  User: Yhurri
  Date: 2020/5/26
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="page_nav">
    <a href="${requestScope.page.url}">
        <c:if test="${requestScope.page.pageNo!=1}">
            首页
        </c:if>

    </a>
    <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo==1 ? 1:requestScope.page.pageNo-1}">上一页</a>
    <%--		情况一 总页码小于5 页码范围1-5；--%>
    <c:choose>
        <c:when test="${requestScope.page.pageTotal<=5}">
            <c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
                <c:if test="${requestScope.page.pageNo==i}">
                    【${i}】
                </c:if>
                <c:if test="${requestScope.page.pageNo!=i}">
                    <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                </c:if>
            </c:forEach>
        </c:when>
        <%--		情况二 总页码大于5 ；--%>
        <c:when test="${requestScope.page.pageTotal>5}">
            <c:choose>
                <c:when test="${requestScope.page.pageNo<=3}">
                    <c:forEach begin="1" end="5" var="i">
                        <c:if test="${requestScope.page.pageNo==i}">
                            【${i}】
                        </c:if>
                        <c:if test="${requestScope.page.pageNo!=i}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                        </c:if>
                    </c:forEach>
                </c:when>
                <c:when test="${requestScope.page.pageNo >= requestScope.page.pageTotal-3}">
                    <c:forEach begin="${requestScope.page.pageTotal-4}" end="${requestScope.page.pageTotal}" var="i">
                        <c:if test="${requestScope.page.pageNo==i}">
                            【${i}】
                        </c:if>
                        <c:if test="${requestScope.page.pageNo!=i}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                        </c:if>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach begin="${requestScope.page.pageNo-2}" end="${requestScope.page.pageNo+2}">
                        <c:if test="${requestScope.page.pageNo==i}">
                            【${i}】
                        </c:if>
                        <c:if test="${requestScope.page.pageNo!=i}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                        </c:if>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>

    <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo==requestScope.page.pageTotal?requestScope.page.pageTotal:requestScope.page.pageNo+1}">下一页</a>
    <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    共${requestScope.page.pageTotal}页，${requestScope.page.count}条记录 到第<input name="pn" id="pn_input" value="${param.pageNo}" >页
    <input type="button" value="确定" id="submit">
</div>

