package pl.sdacademy.wiosnademo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.wiosnademo.domain.Group;
import pl.sdacademy.wiosnademo.services.GroupService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/groups")
public class GroupController {
    private GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Group createGroup(@Valid @RequestBody Group group) {
        return groupService.createGroup(group);
    }

    // /api/groups/{group_name}/users/{username}
    @PostMapping("/{group_name}/users/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addUserToGroup(@PathVariable("group_name") String groupName, @PathVariable("username") String username) {
        groupService.addUserToGroup(groupName, username);
    }

}
