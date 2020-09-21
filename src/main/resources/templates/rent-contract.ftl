<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>居住房屋租赁合同</title>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <style type="text/css">
        *,
        *:before,
        *:after {
            -moz-box-sizing: border-box;
            -webkit-box-sizing: border-box;
            box-sizing: border-box;
        }

        body,
        h1,
        h2,
        h3,
        h4,
        h5,
        h6,
        p,
        dl,
        dt,
        dd,
        ul,
        ol,
        li,
        pre,
        form,
        input,
        textarea,
        th,
        td {
            margin: 0;
            padding: 0;
        }

        html {
            font-size: 100%;
            -ms-text-size-adjust: 100%;
            -webkit-text-size-adjust: 100%;
            font-family: SimSun;
        }

        body {
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
            padding-bottom: 100px;
        }

        body,
        button,
        input,
        select,
        textarea {
            color: #333;
            font: 14px/1.5 "Helvetica Neue", Helvetica, "Hiragino Sans GB",
            "Segoe UI", "Microsoft Yahei", Tahoma, Arial, STHeiti,
            sans-serif;
        }

        h1 {
            font-size: 2em;
        }

        h2 {
            font-size: 1.5em;
        }

        h3,
        h4,
        h5,
        h6 {
            font-size: 1em;
        }

        a {
            background-color: transparent;
            text-decoration: none;
            outline: 0;
        }

        img {
            border: 0;
            -ms-interpolation-mode: bicubic;
        }

        table {
            border-collapse: collapse;
            border-spacing: 0;
        }

        input,
        textarea,
        select {
            outline: 0;
        }

        textarea {
            resize: none;
        }

        ul,
        ol {
            list-style: none;
        }

        body {
            padding: 20px;
            padding-bottom: 10px;
        }

        .contract_title {
            font-size: 24px;
            text-align: center;
            font-weight: bold;
            padding-bottom: 20px;
        }

        dl {
            padding-bottom: 20px;
        }

        dd {
            line-height: 20px;
            padding-bottom: 10px;
        }

        .title {
            font-size: 16px;
            font-weight: bold;
        }

        table {
            width: 100%;
            border: 1px solid #000;
            table-layout: fixed;
            margin-top: 10px;
        }

        td {
            height: 25px;
            padding: 0 5px;
        }

        table tr > td {
            border-right: 1px solid #000;
        }

        .summarize {
            list-style: disc;
            font-weight: 600;
            margin-bottom: 20px;
            margin-left: 20px;
        }

        .agree-title {
            font-weight: bold;
            font-family: 宋体;
            font-size: 14pt;
            text-align: center;
        }

        .important-text {
            font-weight: bold;
        }

        .under-line {
            text-decoration: underline;
        }

        html, body {
            height: 100%;
        }

        #box {
            height: 100%;
            width: 100%;
        }
    </style>
</head>
<body style="font-family:SimSun">
<div id="box">
    <dl>
        <dd>
            合同编号：${contractCode!}
        </dd>
    </dl>
    <h2 class="agree-title" style="font-family:SimSun">居住房屋租赁合同</h2><br/>
    <dl>
        <dd>
            甲方（出租人）：${landlordName!}
        </dd>
        <dd>
            地址：${landlordAddress!}
        </dd>
        <dd>
            乙方（承租人）：${tenantName!}
        </dd>
        <dd>证件号码：${tenantCredentialsNo!}</dd>
        <dd>地址：${tenantAddress!}</dd>
    </dl>
    <dl>
        <dd>根据《中华人民共和国合同法》等有关法律、法规的规定，甲、乙双方在平等、自愿、公平和诚实信用的基础上，经协商一致，就乙方承租甲方可依法出租的房屋事宜，订立本合同。</dd>
        <dd style="font-size: 1.3em; font-weight: bold">租金及支付方式</dd>
        <dd>租赁起止日期：${leaseBeginDate!}至${leaseEndDate!}</dd>
        <dd>租赁期限：${leaseDuration!}</dd>
        <dd>租金付款方式：${paymentMode!}</dd>
        <dd>押金金额：${depositPrice!}元</dd>
        <dd>人民币（大写）：${depositPriceUp!}</dd>
        <dd>每月租金金额：${rentPrice!}元</dd>
        <dd>人民币（大写）：${rentPriceUp!}</dd>
        <dd>每月周期费用：${cycleFee!}元</dd>
        <dd>人民币（大写）：${cycleFeeUp!}</dd>

        <dd style="font-size: 1.3em; font-weight: bold">租客身份信息</dd>
        <dd>姓名：${tenantName!}</dd>
        <dd>性别：${tenantGender!}</dd>
        <dd>联系电话：${tenantPhone!}</dd>
        <dd>证件类型：${tenantCredentialsType!}</dd>
        <dd>证件号码：${tenantCredentialsNo!}</dd>

        <dd style="font-size: 1.3em; font-weight: bold">条款内容</dd>
        <dd class="important-text">第一条 出租房屋</dd>
        <dd>1.甲方将坐落在${tenantAddress!}（简称“该房屋”）出租给乙方。该房屋建筑面积为${houseArea!}平方米。</dd>

        <dd class="important-text">第二条 交付日期和租赁期限</dd>
        <dd>1.甲、乙双方约定，该房屋租赁期起止时间${leaseBeginDate!}至${leaseEndDate!}。</dd>
        <dd>2.双方同意，甲方于${leaseBeginDate!}前将该房屋交付给乙方，由乙方进行验收。</dd>

        <dd class="important-text">第三条 租金及支付方式</dd>
        <dd>1. 乙方使用${paymentMode!}支付租金。</dd>
        <dd>2. 甲、乙双方约定，在租赁期间内，该房屋的房屋押金为${depositPrice!}元，月租金${rentPrice!}元，每月周期费用${cycleFee!}元（不含每月租金${rentPrice!}元）。
        </dd>
        <dd>3. 甲、乙双方约定，乙方应根据${payRentRule!}的支付方式，分别于${everyPeriodPayDate!}
            （乙方系统账户中显示的最晚支付日与本合同约定不一致的，以系统显示的日期为准）支付该房屋租金及每月周期费用，否则每逾期一日，乙方应当向甲方支付承租房屋日租金 <span
                    class="important-text">贰</span> 倍的违约金。
        </dd>

        <dd class="important-text">第四条 其他相关费用</dd>
        <dd>1. 租赁期限内，该房屋所发生的 ：水费、电费、煤气费、有线电视费、网络宽带费、物业管理费、室内设施维修费、保洁费、暖气费均由 【乙方】承担。</dd>
        <dd>2.
            甲方向乙方收取本条项下约定由乙方承担的费用（如水、电、煤气等费用）后统一缴纳的，应根据法律法规或相关规定如实计收该等费用（费用标准以国家电网、当地燃气集团、自来水公司等服务提供商制定的为准）。否则，乙方有权拒绝支付虚高部分价格，并保留追究甲方违约责任的权利。
        </dd>

        <dd class="important-text">第五条 联系方式、生效条件及其他</dd>
        <dd>甲方联系地址：${landlordAddress!}，联系电话：${landlordPhone!}</dd>
        <dd>乙方联系地址：${tenantAddress!}，联系电话：${tenantPhone!}</dd>
        <dd>1. 除非甲方或者乙方变更联系方式并且告知对方，并确保对方收悉，否则以上联系方式即为甲乙双方联系方式。甲、乙双方向前述对方联系地址以挂号信方式邮寄法律文书的，即视为法律文书已经通知并送达对方。</dd>
        <dd>2.
            本合同为电子合同，经乙方在本系统点击“确认签署”或支付本合同项下账单（该房屋租金及/或押金账单）后生效。任意一方需对本合同进行公证或认证的，有权要求全房通房东版租房平台提供电子合同服务，由此产生的费用（包括但不限于公证费用、认证费用）由需求方承担。
        </dd>
        <dd>3. 本合同生效后，甲、乙双方对合同内容的变更或补充应另行订立电子合同，作为本合同的附件。本合同附件与本合同具有同等法律效力。</dd>
        <dd>4. 本合同第一条至第五条内容与其他条款内容不一致的，以本合同第一条至第五条内容为准。</dd>

        <dd class="important-text">第六条 附件内容</dd>
        <dd>备注及补充条款：</dd>
        <dd>房间家具清单：${contractSettings!}</dd>

        <dd class="important-text">第七条 甲方权利义务</dd>
        <dd>1. 甲方应当按照本合同约定按时向乙方交付房屋。若甲方逾期未按照本合同约定向乙方交付该房屋的，每逾期一日，则甲方需向乙方支付承租房屋日租金 贰
            倍的违约金。该房屋交付验收时，现有装修、附属设施、设备存在缺陷影响乙方正常使用的，甲方应自交付知道或应当知道缺陷之日起的5日内进行修复。
        </dd>
        <dd>2.
            甲方应确保该房屋交付时符合规定的安全条件。租赁期间，该房屋及本合同附件中列明的附属设施、设备有损坏或故障的，若非因乙方故意或过失导致损坏或故障的，则由甲方承担维修责任，否则，由乙方承担维修责任。乙方发现该房屋及本合同附件中列明的附属设施、设备有损坏或故障时，应及时通知甲方修复，否则，由此导致的情况恶化、损失扩大的，由乙方承担责任；甲方应在接到乙方通知后的
            2 日内进行维修或委托乙方进行维修。甲方逾期不维修、也不委托乙方进行维修的，乙方可代为维修，费用由甲方承担。
        </dd>

        <dd class="important-text">第八条 乙方权利义务</dd>
        <dd>1. 乙方向甲方承诺，承租该房屋仅用作个人居住使用，不得转租。</dd>
        <dd>2. 租赁期间，乙方可使用的该房屋公用或合用部位的使用范围，现有的装修、附属设施、设备状况以及需约定的有关事项，将在 《家具家电清单》 中列明，该清单将作为甲方交付该房屋和本合同终止时乙方返还该房屋的验收依据。
        </dd>
        <dd>3.
            乙方应对该房屋的使用安全负责。租赁期间，乙方应合理使用并爱护该房屋及其附属设施、设备，因乙方使用不当或不合理使用，致使该房屋及其附属设施、设备损坏或发生故障的，乙方应负责修复。乙方不维修的，甲方可代为维修，费用由乙方承担。
        </dd>
        <dd>4. 租赁期间，最后一期租期不足期的，双方同意以乙方实际居住天数计算该期租金。在租赁期间内，未经双方协商一致，任何一方不得擅自调整租金标准。</dd>
        <dd>5. 租赁期满后甲、乙双方确认不续租或本合同解除后，房屋押金除用以抵扣应由乙方承担但尚未交纳的费用（包括但不限于租金、违约金等本合同约定由乙方承担的费用）外，剩余部分（若有）应无息退还乙方。</dd>
        <dd>6. 该房屋租赁期限届满、乙方需继续承租的，乙方应于租赁期限届满前三十日内提出续租申请，经甲、乙双方协商一致后，另行签订书面协议。乙方不续租的，甲方有权在租赁期限届满前三十日内带人看房，乙方需给予配合。</dd>
        <dd>7. 除甲方同意乙方续租外，乙方应在本合同的租期届满后 1 日内返还该房屋。乙方未经甲方书面同意逾期返还该房屋的，每逾期一日，乙方应按该房屋日租金的 叁
            倍向甲方支付房屋占用费。除本合同附件另有约定外，乙方返还该房屋时，该房屋及其装修、附属设施和设备应当符合正常使用后的状态。经甲方书面验收认可后，相互结清各自应当承担的费用。
        </dd>
        <dd>8. 本合同有效期内，乙方无转租权，并自愿放弃优先购买权。</dd>

        <dd class="important-text">第九条 解约条件</dd>
        <dd>甲、乙双方同意在租赁期内，有下列情形之一的，本合同解除，双方互不担责：</dd>
        <dd>（一）该房屋占用范围内的土地使用权依法提前收回的；</dd>
        <dd>（二）该房屋因公共利益需要被依法征收的；</dd>
        <dd>（三）该房屋因城市建设需要被依法列入房屋拆迁许可范围的；</dd>
        <dd>（四）该房屋因不可抗力原因毁损、灭失，致使乙方不能正常使用的；</dd>
        <dd>（五）签订本合同时，甲方已告知乙方该房屋已设定抵押，租赁期间被处分的；</dd>
        <dd>（六）甲乙双方协商一致解除本合同的。</dd>
        <dd>甲、乙双方同意，甲方有下列情形之一的，乙方可书面通知甲方解除本合同，并有权要求甲方赔偿损失。</dd>
        <dd>（一）甲方未按合同约定按时交付该房屋，经乙方书面催告后 3 日内仍未交付的；</dd>
        <dd>（二）甲方交付的该房屋不符合本合同约定或存在重大质量缺陷，致使乙方不能正常使用的。</dd>
        <dd>甲、乙双方同意，乙方有下列情形之一的，甲方可书面通知乙方解除本合同，并有权要求乙方赔偿损失。</dd>
        <dd>（一）乙方擅自改变房屋居住用途的；</dd>
        <dd>（二）因乙方原因造成房屋结构损坏的；</dd>
        <dd>（三）乙方擅自转租该房屋、转让该房屋承租权或与他人交换各自承租的房屋的；</dd>
        <dd>（四）乙方擅自增加承租同住人，或人均承租建筑面积、使用面积低于规定标准的（居住房屋人均建筑面积不低于10平方米，且人均使用面积不低于7平方米）；</dd>
        <dd>（五）乙方利用承租的居住房屋从事违法违规活动的；</dd>
        <dd>（六）乙方逾期不支付本合同项下约定由乙方承担的费用的（包括但不限于房屋押金、租金、水电煤费用等）；</dd>
        <dd>（七）乙方擅自将该房屋钥匙交付或配给非居住人员的；</dd>
        <dd>（八）乙方隐瞒、漏报、谎报自身传染性病症或隐性病症的；</dd>
        <dd>（九）乙方在未承租的房间堆放私人物品的。</dd>

        <dd class="important-text">第十条 违约责任</dd>
        <dd>1. 本协议有效期内，任意一方未履行或未完全履行本协议约定的，应承担相应的违约责任。守约方有权要求违约方支付相当于一个月房屋租金的违约金，若违约金无法弥补守约方损失，违约方还应负责赔偿。</dd>

        <dd class="important-text">第十一条 解决争议的方式</dd>
        <dd>1. 本合同由中华人民共和国法律、法规管辖。</dd>
        <dd>2. 双方在履行本合同过程中若发生争议的，甲、乙双方可协商解决或者向人民调解委员会申请调解，也可依法向签约地人民法院提起诉讼。</dd>

        <dd class="important-text">第十二条 免责条件</dd>
        <dd>1. 因不可抗力原因致使本合同不能继续履行或造成的损失，甲、乙双方互不承担责任。</dd>
        <dd>2. 因国家政策需要拆除或改造已租赁的房屋，使甲、乙双方造成损失的，互不承担责任。</dd>

        <dd class="important-text">第十三条 其他说明</dd>
        <dd>1. 本合同涉及金额为不含税金额，如乙方需要申报个税抵扣，费用由乙方自行承担。</dd>
    </dl>
</div>
</body>
</html>