package pl.sdacademy.wiosnademo.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sdacademy.wiosnademo.domain.Group;
import pl.sdacademy.wiosnademo.domain.User;
import pl.sdacademy.wiosnademo.repositories.GroupRepository;

@Service
@Transactional
public class GroupService {
    private GroupRepository groupRepository;
    private UserCrudService userCrudService;

    public GroupService(GroupRepository groupRepository, UserCrudService userCrudService) {
        this.groupRepository = groupRepository;
        this.userCrudService = userCrudService;
    }

    public Group createGroup(Group group) {
        groupRepository.findById(group.getName())
                .ifPresent(existingGroup -> {
                    throw new ApplicationException("Group already exists");
                });
        return groupRepository.save(group);
    }

    public void addUserToGroup(String groupName, String username) {
        //czy grupa istnieje
        Group group = groupRepository.findByGroupNameWithUsers(groupName).orElseThrow(() -> new ApplicationException("requested group doesnt exist"));
        //czy użytk istnieje
        User user = userCrudService.getUserByUsername(username);
        //czy użytk jest w grupie
        boolean userAlreadyGroupMember = group.getUsers().stream()
                .anyMatch(usr -> usr.getUsername().equalsIgnoreCase(username));
        if ((userAlreadyGroupMember)) {
            throw new ApplicationException("User already member of requested group");
        }

        group.getUsers().add(user);
    }
}
