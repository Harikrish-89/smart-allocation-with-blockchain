import { Component, OnInit, ViewChild } from "@angular/core";
import { SelectionModel } from "@angular/cdk/collections";
import { ViewPendingAllocationServiceService } from "../pending-allocations-service.service";
import {ReviewedAllocationServiceService} from "../reviewed-allocations-service.service";
import { Allocation } from "../allocation";
import { Observable, BehaviorSubject } from "rxjs";
import { MatPaginator, MatTableDataSource, MatSort } from "@angular/material";
import { AllocationChangeRequest } from "../allocationChangeRequest";

@Component({
  selector: "app-pending-allocations",
  templateUrl: "./pending-allocations.component.html",
  styleUrls: ["./pending-allocations.component.css"]
})
export class ViewPendingAllocationComponent implements OnInit {
  public allocationChangeRequests: AllocationChangeRequest[];

  public isLoading: Boolean = true;
  public isError: Boolean = false;

  public isModificationError: boolean = false;
  public isModificationSuccess: boolean = false;



  displayedColumns: string[] = [
    "select",
    "requestId",
    "allocationId",
    "clientOrg",
    "clientId",
    "parentPortfolio",
    "status",
    "tolerances",
    "targetWeight",
    "shareClassConfigFlag",
    "invstFrozenConfig",
    "targetCash",
    "overdraftLimit",
    "minimumTradeSize",
    "childPortFolio",
    "fundName",
    "rejectReason",
    "createdTime"
  ];

  public allocationsDataSource: MatTableDataSource<AllocationChangeRequest>;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  @ViewChild(MatSort) sort: MatSort;


  public selectedAllocationChangeRequest: SelectionModel<AllocationChangeRequest> = new SelectionModel<
  AllocationChangeRequest>(false, []);

  constructor(
    private viewPendingAllocationService: ViewPendingAllocationServiceService,
    private reviewedAllocationService: ReviewedAllocationServiceService
  ) {}

  onApprove(){
    let allocationChangeRequest:AllocationChangeRequest = this.selectedAllocationChangeRequest.selected[0];
    allocationChangeRequest.status = "Approved";
    this.postReviewedAllocation(allocationChangeRequest);
  }

  onReject(){
    let allocationChangeRequest:AllocationChangeRequest = this.selectedAllocationChangeRequest.selected[0];
    allocationChangeRequest.status = "Rejected";
    this.postReviewedAllocation(allocationChangeRequest);
  }

  private postReviewedAllocation(allocationChangeRequest:AllocationChangeRequest): void{
    this.isLoading = true;
    this.reviewedAllocationService.postReviewedAllocation(allocationChangeRequest).subscribe(
      data => {
        this.isModificationSuccess = true;
        this.loadPendingAllocations();
        this.isLoading = false;
      },
      error => {
        this.isModificationSuccess = false;
        this.isModificationError = true;
        this.loadPendingAllocations();
        this.isLoading = false;
        console.log(error.message);
        this.isError = true;
      }
    );

  }

  loadPendingAllocations() {
    this.viewPendingAllocationService.getPendingAllocations().subscribe(
      data => {
        this.allocationChangeRequests = data;
        this.allocationsDataSource = new MatTableDataSource(this.allocationChangeRequests);
        this.allocationsDataSource.paginator = this.paginator;
        this.allocationsDataSource.sort = this.sort;
        this.isLoading = false;
      },
      error => {
        this.isLoading = false;
        console.log(error.message);
        this.isError = true;
      }
    );
  }

  ngOnInit() {
    this.loadPendingAllocations();
  }
}
