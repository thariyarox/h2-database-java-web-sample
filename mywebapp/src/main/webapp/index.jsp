<%@ page import="java.util.ArrayList" %>
<%@ page import="com.wso2.sample.Student" %>
<%@ page import="com.wso2.sample.App" %>
<html>
<head><title>Student App</title></head>
<body>
<h2>Listing Students</h2>

<table border="1">
    <thead>ID</thead>
    <thead>Name</thead>

    <%
        ArrayList<Student> students = App.getStudents();

        for (Student student : students) {

            int id = student.getId();
            String name = student.getName();
    %>

    <tr>

        <td>
            <%=id%>
        </td>

        <td>
            <%=name%>
        </td>

    </tr>

    <%
        }
    %>

</table>

</body>
</html>
