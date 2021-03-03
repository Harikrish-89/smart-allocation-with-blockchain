import { Group } from "./Group";
import { AppRole } from "./AppRole";

export class ApplicationUser {
  public userId: String;

  public userDisplayName: String;

  public userLoginId: String;

  public organisation: String;

  public groups: Group[];

  public appRoles: AppRole[];

  public selectedRole: String;
}
