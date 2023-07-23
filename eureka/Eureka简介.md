# Eureka简介

一、介绍

> Eureka is a REST (Representational State Transfer) based service that is primarily used in the AWS cloud for locating services for the purpose of load balancing and failover of  middle-tier servers.
>
> Eureka是一种基于REST（Representational State Transfer）的服务，主要用于AWS云中定位服务，以实现中间层服务器的负载平衡和故障切换。

> At Netflix, Eureka is used for the following purposes apart from playing a critical part in mid-tier load balancing.
>
> - For aiding Netflix Asgard - an open source service which makes cloud deployments easier, in
>   - Fast rollback of versions in case of problems avoiding the re-launch of 100's of instances which could take a long time.
>   - In rolling pushes, for avoiding propagation of a new version to all instances in case of problems.
> - For our cassandra deployments to take instances out of traffic for maintenance.
> - For our memcached caching services to identify the list of nodes in the ring.
> - For carrying other additional application specific metadata about services for various other reasons.
>
> 在Netflix，除了在中间层负载平衡中扮演关键角色外，Eureka还用于以下目的。

> 特点
>
> 1. 属于Spring Cloud  Netflix 的一个子模块
> 2. 遵循AP原则
> 3. restful 风格
> 4. 