<div style="text-align: center;">

    <h2>
        <%
            if (session.getAttribute("login") == null || session.getAttribute("login") == "") {//check if condition for unauthorize user not direct access welcome.jsp page
                response.sendRedirect("index.jsp");
            }
        %>

        Welcome, <%=session.getAttribute("login")%>
    </h2>

    <h3>
        <a href="logout">Logout</a>
    </h3>

</div>

