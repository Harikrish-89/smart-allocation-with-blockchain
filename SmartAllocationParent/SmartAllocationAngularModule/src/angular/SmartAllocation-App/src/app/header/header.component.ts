import { Component, OnInit } from "@angular/core";
import { UserDataServiceService } from "../user-data-service.service";

@Component({
  selector: "app-header",
  templateUrl: "./header.component.html",
  styleUrls: ["./header.component.css"]
})
export class HeaderComponent implements OnInit {
  public hasSignedIn: boolean = false;

  public userName: String;

  constructor(private userDataService: UserDataServiceService) {}

  ngOnInit() {
    this.userDataService.isAuthenticationSuccess.subscribe(
      data => (this.hasSignedIn = data)
    );
    this.userDataService.userName.subscribe(
      data => (this.userName = data)
    );
  }
}
