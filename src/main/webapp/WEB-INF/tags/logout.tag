<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="title"%>

<div class="row">
    <div class="col-sm-6">
        <nav class="navbar navbar-default">
            <p class="navbar-text">${title}</p>
            <div class="container-fluid">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="/logOut"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                </ul>
            </div>
        </nav>
    </div>
</div>
