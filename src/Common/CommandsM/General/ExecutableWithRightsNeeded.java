package Common.CommandsM.General;

import Common.Security.User;

public interface ExecutableWithRightsNeeded extends Executable{

    void setUser(User user);

}
