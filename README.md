# Contracts
Eclipse | JUnit | JSON.simple | Gradle | .json file

· Scan: Represents a document. It has a file name, some text content and a page count.                                                                                                
· Company: Represents a company, mandatory fields are id and name.                                                                                                                    
· Agreement: Represents a contract between two companies ("My Company" and "Counterparty Company") and contains a set of scans.

· Add the ability to add and remove scans from a contract                                                                                                                              
· Given a contract, get the total page count based on the scans contained in the contract.                                                                                          
· Given a list of contracts and a company id: get a list of the file names from all the scans contained in contracts where the company acting as "My Company" matches the input id.    
· Given a list of contracts, group them by counterparty company and return a map structure so that the key is counterparty id and the value is the list of contracts for that company.

Verify the expected behavior by providing some unit tests.
