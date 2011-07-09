/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function processAjax(data, f) {
  if (data && data.code == 1) {
    alert("error processing the request: " + data.err);
  } else {
    f(data);
  }
}
function doAjax(url, f) {
  $.getJSON(url, function(data) {
    processAjax(data, f)
  });
}
function disableUser(u)
{
  var url = 'disableuser?login=' + u;
 doAjax(url, function(data) {
    //alert(data.msg);
    location.reload();
 });
}
function enableUser(u)
{
  var url = 'enableuser?login=' + u;
  doAjax(url, function(data) {
    //alert(data.msg);
    location.reload();
  });
}
function resetUserPwd(u)
{
  var url = 'resetpassword?login=' + u;
  doAjax(url, function(data) {
    alert(data.msg);
    location.reload();
  });
}
