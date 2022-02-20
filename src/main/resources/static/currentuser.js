const linkGetUser = "http://localhost:8080/api/currentUser";

async function fetchCurrentuser() {
    return await (await fetch(linkGetUser)).json();
}

async function getCurrentuser () {
 const currentUser = await fetchCurrentuser();
 const template = `
  <tr>
     <td>
         <div class="form-group">
             <div id="name">${currentUser.name}</div>
         </div>
     </td>
     <td>
         <div class="form-group">
             <div id="lastname">${currentUser.lastName}</div>
         </div>
     </td>
     <td>
         <div class="form-group">
             <div id="email">${currentUser.email}</div>
         </div>
     </td>
     <td>
         <div class="form-group">
             <div id="age">${currentUser.age}</div>
         </div>
     </td>
 </tr>
 `
 if (currentUser.rolesString.includes('ROLE_ADMIN')) {
  const tmpLink = `
     <li class="nav-item">
        <a href="/admin/users" class="nav-link">Admin</a>
    </li>
    <li class="nav-item">
        <a href="/user" class="btn btn-primary btn-lg btn-block text-left" role="button">User</a>
    </li>
  `
  document.getElementById("side-nav-bar").innerHTML = tmpLink;
 }
 document.getElementById("tbodyUser").innerHTML = template;
}

getCurrentuser()