function processAjax(data, f) {
  if (data && data.code == 1) {
    showMsgError(data.err);
  } else {
    if (f) {
      f(data);      
    }
  }
}
function doAjax(url, f) {
  $.getJSON(url, function(data) {
    processAjax(data, f)
  });
}
function doAjaxPost(url, f) {
  $.post(url, function(data) {
    processAjax(data, f)
  }).error(function(jqXHR, textStatus, errorThrown) {
    if (jqXHR.status == 400) {
      var obj = jQuery.parseJSON(jqXHR.responseText)
      alert(obj[0]);
    } else {
      alert(textStatus + " " + jqXHR.status + ": " + errorThrown);
    }
  });
}
function getGroups(f)
{
  var url = 'resources/groups';
  doAjax(url, f);
}
function getGroup(g, f)
{
  var url = 'resources/groups/' + g;
  doAjax(url, f);
}
function addMember(g, u, f)
{
  var url = 'resources/groups/' + g +'/addmember/' + u;
  doAjaxPost(url, f, function() {
    //location.reload();    
  });
}
function delMember(g, u, f)
{
  var url = 'resources/groups/' + g +'/delmember/' + u;
  doAjaxPost(url, f, function() {
    //location.reload();    
  });
}

function newGroup(g, f)
{
  var url = 'resources/groups/addgroup/' + g;
  doAjaxPost(url, f);
}

function showMsgInfo(msg)
{
  $('#dialog-message-type').removeClass('ui-state-error').addClass('ui-state-highlight');
  showMsgDialog('Info', msg);
}

function showMsgError(msg)
{
  $('#dialog-message-type').removeClass('ui-state-highlight').addClass('ui-state-error');
  showMsgDialog('Error', msg);
}

function showMsgDialog(title, msg)
{
  $('#dialog-message .msg').text(msg);
  $('#dialog-message').dialog({
    title: title,
    modal: true,
    buttons: {
      Ok: function() {
        $(this).dialog( "close" );
        location.reload();
      }
    }
  });
}

$(document).ready(function() {
  $('.addmember').click(function() {
    var s = $('#addusers');
    s.empty();
    var members = [];
    $.each($(this).siblings('.umember'), function() {
      var name = $(this).find('.member');
      members.push(name.text());
    });
    users.sort();
    for (var u in users) {
      var n = users[u];
      if ($.inArray(n, members) >= 0) {
        continue;
      }
      s.append('<option>' + n + '</option>');
    }
    var n = $(this).parents('.groupname').find('.gname');
    var gname = n.text();
    var o = {
        buttons: {
          'OK': function() {
            var s = $('#addusers option:selected').each(function() {
              var uname = $(this).text();
              addMember(gname, uname)
            });
            $(this).dialog('close');
            location.reload();
          },
          'Cancel': function() {
            $(this).dialog('close');
          }
        }
    }
    $('.adduser_dialog').dialog(o);
  });
  $('.removemember').click(function() {
    var u = $(this).siblings('.member').text();
    var n = $(this).parents('.groupname').find('.gname');
    var gname = n.text();
    
    delMember(gname, u);
    location.reload();
  })
  $('.addgroup.button').click(function() {
    $('.addgroup_dialog .name').val('');
    var o = {
        buttons: {
          'OK': function() {
            var name = $('.addgroup_dialog .name').val();
            newGroup(name, function() {
              $(this).dialog('close');
              location.reload();              
            });
          },
          'Cancel': function() {
            $(this).dialog('close');
          }
        }
    }
    $('.addgroup_dialog').dialog(o);    
  });
});
