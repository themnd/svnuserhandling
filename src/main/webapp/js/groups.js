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
  doAjaxPost(url, f);
}
function delMember(g, u, f)
{
  var url = 'resources/groups/' + g +'/delmember/' + u;
  doAjaxPost(url, f);
}

function newGroup(g, f)
{
  var url = 'resources/groups/addgroup/' + g;
  doAjaxPost(url, f);
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
            var names = '';
            var s = $('#addusers option:selected').each(function() {
              var uname = $(this).text();
              if (names != '') {
                names += ',';
              }
              names += uname;
            });
            if (names != '') {
              addMember(gname, names, function() {
                $(this).dialog('close');
                location.reload();                
              })              
            }
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
    
    delMember(gname, u, function() {
      location.reload();      
    });
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
