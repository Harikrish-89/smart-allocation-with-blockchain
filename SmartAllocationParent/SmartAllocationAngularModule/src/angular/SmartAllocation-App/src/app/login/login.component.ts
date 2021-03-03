import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { AuthenticationServiceService } from "../authentication-service.service";
import { UserDataServiceService } from "../user-data-service.service";
import { ApplicationUser } from "../applicationUser";
import { BehaviorSubject } from "rxjs";

const roleMap: Map<String, String> = new Map<String, String>();
roleMap.set("Auditor", "App Monitors");
roleMap.set("Client", "App Operators");
roleMap.set("Approver", "App Administrators");

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"]
})
export class LoginComponent implements OnInit {
  constructor(
    private router: Router,
    private authenticationService: AuthenticationServiceService,
    private userDataService: UserDataServiceService
  ) {}

  public userName: String;

  public password: String;

  public organisation: String;

  public role: String = "";

  public isAuthenticationError: boolean = false;

  public isLoadingForAuthentication: boolean = false;

  public errorMessage: String;

  ngOnInit() {}

  performLogin() {
    this.isLoadingForAuthentication = true;
    let encodedCreds: String = btoa(this.userName + ":" + this.password);
    this.userDataService.encodedCreds = encodedCreds;
    this.authenticationService.authenticate(encodedCreds).subscribe(
      data => {
        this.userDataService.applicationUser = data;
        this.userDataService.applicationUser.organisation = this.organisation;
        this.userDataService.applicationUser.selectedRole = this.role;
        if (this.verifyUserRole(this.userDataService.applicationUser)) {
          this.router.navigateByUrl("/userHome");
          this.isLoadingForAuthentication = false;
          this.userDataService.isAuthenticationSuccess.next(true);
          this.userDataService.userName.next(
            this.userDataService.applicationUser.userDisplayName
          );
        } else {
          this.processForError("Invalid user role selected");
        }
      },
      error => {
        this.processForError(
          "Authentication error unable to login please check username and password"
        );
      }
    );
  }

  private processForError(errorMessageValue: String) {
    this.isAuthenticationError = true;
    this.isLoadingForAuthentication = false;
    this.errorMessage = errorMessageValue;
  }

  private verifyUserRole(applicationUser: ApplicationUser): boolean {
    let isVerificationSuccess: boolean = false;
    applicationUser.appRoles.forEach(role => {
      if (role.display === roleMap.get(this.role)) {
        isVerificationSuccess = true;
      }
    });
    return isVerificationSuccess;
  }

  public roleChange(event) {
    this.role = "" + event;
  }
}
