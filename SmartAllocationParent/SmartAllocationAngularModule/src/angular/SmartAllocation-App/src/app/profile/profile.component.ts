import { map } from "rxjs/internal/operators";
import { Component, OnInit } from "@angular/core";
import { UserDataServiceService } from "../user-data-service.service";
import { ApplicationUser } from "../applicationUser";
import { Observable, of } from "rxjs";
import { Router } from "@angular/router";

const roleMap: Map<String, String> = new Map<String, String>();
roleMap.set("App Monitors", "Auditor");
roleMap.set("App Operators", "Client");
roleMap.set("App Administrators", "Approver");

@Component({
  selector: "app-profile",
  templateUrl: "./profile.component.html",
  styleUrls: ["./profile.component.css"]
})
export class ProfileComponent implements OnInit {
  public role: String;

  public userId: String;

  public userName: String;

  public organisation: String;

  public allowedRoles: Object[];

  constructor(private userDataService: UserDataServiceService,private router: Router) {}

  ngOnInit() {
    this.role = this.userDataService.applicationUser.selectedRole;
    this.userId = this.userDataService.applicationUser.userLoginId;
    this.organisation = this.userDataService.applicationUser.organisation;
    this.userName = this.userDataService.applicationUser.userDisplayName;
    this.allowedRoles = this.getAllowedRoles(
      this.userDataService.applicationUser
    );
  }

  getAllowedRoles(applicationUser: ApplicationUser): Object[] {
    let allowedRoles: Object[] = [];
    applicationUser.appRoles.forEach(appRole => {
      let allowedRole: Object = {
        name: roleMap.get(appRole.display),
        value: roleMap.get(appRole.display)
      };
      allowedRoles.push(allowedRole);
    });
    return allowedRoles;
  }

  public roleChange(event) {
    this.role = "" + event;
  }

  public changeOrganisationAndRole() {
    this.userDataService.applicationUser.selectedRole = this.role;
    this.userDataService.applicationUser.organisation = this.organisation;
    this.router.navigateByUrl("/userHome")
  }
}
