import { Component, OnInit } from "@angular/core";
import { UserDataServiceService } from "../user-data-service.service";

@Component({
  selector: "app-user-home",
  templateUrl: "./user-home.component.html",
  styleUrls: ["./user-home.component.css"]
})
export class UserHomeComponent implements OnInit {
  public showSearchAllocation: boolean = false;

  public showViewAllocation: boolean = false;

  public showModifyAllocation: boolean = false;

  public showReviewAllocation: boolean = false;

  constructor(private userDataService: UserDataServiceService) {
    
  }

  ngOnInit() {
    if (this.userDataService.applicationUser.selectedRole === "Auditor") {
      this.showSearchAllocation = true;
    }
    if (this.userDataService.applicationUser.selectedRole === "Client") {
      this.showViewAllocation = true;
      this.showModifyAllocation = true;
    }
    if (this.userDataService.applicationUser.selectedRole === "Approver") {
      this.showReviewAllocation = true;
      this.showSearchAllocation = true;
    }
  }
}
